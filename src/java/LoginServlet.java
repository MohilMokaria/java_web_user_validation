
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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

        // Retrieve user login information from the request parameters
        String email = request.getParameter("mail");
        String pass = request.getParameter("pass");

        try {
            // Load the JDBC driver class
            Class.forName(jdbcUri);

            // Establish a connection to the database
            try (Connection con = DriverManager.getConnection(dbUri, dbId, dbPass)) {
                // Prepare a SQL statement to check the user's login credentials
                PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE mail=? AND pass=?");

                // Set parameters for the prepared statement
                ps.setString(1, email);
                ps.setString(2, pass);

                // Execute the query
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // If login is successful, create a session and set the user's email as a session attribute
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", email);

                    // Redirect to the home page after successful login
                    response.sendRedirect("./homePage.jsp");
                } else {
                    // If login fails, display an error message
                    pw.println("Invalid Login Credentials!");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            // Log any exceptions that occur during the database operation
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            pw.println("Error: " + ex.getMessage());
        }
    }
}
