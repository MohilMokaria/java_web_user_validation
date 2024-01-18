
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve database connection parameters from servlet context
        String jdbcUri = getServletContext().getInitParameter("jdbcUri");
        String dbUri = getServletContext().getInitParameter("dbUri");
        String dbId = getServletContext().getInitParameter("dbId");
        String dbPass = getServletContext().getInitParameter("dbPass");

        // Set the response content type to HTML
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        // Retrieve user registration information from the request parameters
        String email = request.getParameter("mail");
        String pass1 = request.getParameter("pass1");

        try {
            // Load the JDBC driver class
            Class.forName(jdbcUri);

            // Establish a connection to the database
            try (Connection con = DriverManager.getConnection(dbUri, dbId, dbPass)) {
                // Prepare a SQL statement to check if the email is already registered
                PreparedStatement ps1 = con.prepareStatement("SELECT * FROM login WHERE mail=?");
                ps1.setString(1, email);

                // Execute the query
                ResultSet rs = ps1.executeQuery();

                if (rs.next()) {
                    // If the email is already registered, display a message and provide links for the user
                    pw.println("<h1>User already registered with " + email + "</h1>");
                    pw.println("<br><a href=\"./index.jsp\">Try Again with new email</a>");
                    pw.println("<br><br><a href=\"./login.jsp\">Login to existing account</a>");
                } else {
                    // If the email is not registered, insert the new user into the database
                    PreparedStatement ps2 = con.prepareStatement("INSERT INTO login(mail, pass) values(?,?)");
                    ps2.setString(1, email);
                    ps2.setString(2, pass1);

                    // Execute the update query
                    int i = ps2.executeUpdate();

                    // Redirect to the login page after successful registration
                    response.sendRedirect("./login.jsp");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Log any exceptions that occur during the database operation
            Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            pw.println("Error: " + ex.getMessage());
        }
    }
}
