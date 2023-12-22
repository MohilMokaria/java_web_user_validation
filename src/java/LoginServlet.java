
import jakarta.servlet.ServletException;
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

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jdbcUri = "com.mysql.cj.jdbc.Driver";
        String dbUri = "jdbc:mysql://localhost:3306/mysql";
        String dbId = "root";
        String dbPass = "";
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
                    response.sendRedirect("./home_page.html");
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
