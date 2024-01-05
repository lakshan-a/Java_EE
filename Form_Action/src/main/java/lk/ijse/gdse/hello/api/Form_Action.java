package lk.ijse.gdse.hello.api;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Customer" , urlPatterns = "/Customer", loadOnStartup = 1, initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "12345"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/gdse66_hello")
})

public class Form_Action extends HttpServlet {

    private String username;
    private String password;
    private String url;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");
        String name =req.getParameter("name");
        String address =req.getParameter("address");
        String salary =req.getParameter("salary");


    }
}
