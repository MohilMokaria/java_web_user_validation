
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the session, if it exists
        HttpSession session = request.getSession(false);

        // Check if the session is not null (i.e., the user is logged in)
        if (session != null) {
            // Invalidate (close) the session to log the user out
            session.invalidate();
        }

        // Redirect to the login page after logout
        response.sendRedirect("login.jsp");
    }
}
