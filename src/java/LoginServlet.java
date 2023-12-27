
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

        String jdbcUri = getServletContext().getInitParameter("jdbcUri");
        String dbUri = getServletContext().getInitParameter("dbUri");
        String dbId = getServletContext().getInitParameter("dbId");
        String dbPass = getServletContext().getInitParameter("dbPass");
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String email = request.getParameter("mail");
        String pass = request.getParameter("pass");

        try {
            Class.forName(jdbcUri);

            try (Connection con = DriverManager.getConnection(dbUri, dbId, dbPass)) {
                PreparedStatement ps = con.prepareStatement("SELECT * FROM login WHERE mail=? AND pass=?");

                ps.setString(1, email);
                ps.setString(2, pass);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", email);
                    response.sendRedirect("./homePage.jsp");
                } else {
                    pw.println("Invalid Login Credentials!");
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            pw.println("Error: " + ex.getMessage());
        }
    }
}
