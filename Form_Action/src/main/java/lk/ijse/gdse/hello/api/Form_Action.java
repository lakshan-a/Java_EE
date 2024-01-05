package lk.ijse.gdse.hello.api;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Exact_Mapping" , value = "/hello")

public class Form_Action extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");
        String name =req.getParameter("name");
        String address =req.getParameter("address");
        String salary =req.getParameter("salary");
    }
}
