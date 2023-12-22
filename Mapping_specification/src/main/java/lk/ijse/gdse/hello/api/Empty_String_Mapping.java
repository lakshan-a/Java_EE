package lk.ijse.gdse.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Empty_String_Mapping" , value = "*.php")
//@WebServlet(name = "Empty_String_Mapping" , value = "/*.php") ----Invaled-----
//@WebServlet(name = "Empty_String_Mapping" , value = "abc*.php") ----Invaled-----


public class Empty_String_Mapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html>" +
                "<body>" +
                "<h1>Hello I am Empty String Mapping </h1>" +
                "</body>" +
                "</html>");
    }
}
