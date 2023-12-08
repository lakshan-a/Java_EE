package lk.ijse.gdse.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Hello_Servlet",value = "/text",loadOnStartup = 1,initParams = {
        @WebInitParam(name = "username" , value = "root"),
        @WebInitParam(name = "password" , value = "12345"),
        @WebInitParam(name = "url" , value = "jdbc:mysql://localhost:3306/gdse66_hello")
})
public class Hello_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
    }
}
