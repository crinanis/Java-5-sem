import com.sun.messaging.ConnectionConfiguration;
import com.sun.messaging.ConnectionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "ServletProducer", value = "/ServletProducer")
public class ServletProducer extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        PrintWriter writer = response.getWriter();
        String message = request.getParameter("message");
        Connect c1=new Connect(message);
    }
}