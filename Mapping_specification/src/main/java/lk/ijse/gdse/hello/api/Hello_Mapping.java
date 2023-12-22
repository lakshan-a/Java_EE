package lk.ijse.gdse.hello.api;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Hello_Mapping" , value = "/hello")

public class Hello_Mapping extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>" +
                            "<body>" +
                                    "<h1>Hello I am Lakshan </h1>" +
                            "</body>" +
                        "</html>");
    }
}
