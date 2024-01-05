package lk.ijse.gdse.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Extension_Mapping" , value = "*.php")
//@WebServlet(name = "Empty_String_Mapping" , value = "/*.php") ----Invaled-----
//@WebServlet(name = "Empty_String_Mapping" , value = "abc*.php") ----Invaled-----

public class Extension_Mapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Hello I am Extension Mapping (*.php) </h1>");

        System.out.println("-----------Extension Mapping----------");
        System.out.println("1 : "+req.getContextPath());
        System.out.println("2 : "+req.getPathInfo());
        System.out.println("3 : "+req.getServletPath());
        System.out.println("4 : "+req.getPathTranslated());
        System.out.println("5 : "+req.getQueryString());
        System.out.println("6 : "+req.getRequestURI());
        System.out.println("7 : "+req.getRequestURL());
        System.out.println("8 : "+req.getProtocol());
        System.out.println("9 : "+req.getScheme());
        System.out.println("10 : "+req.getRemoteAddr());
        System.out.println("11 : "+req.getRemotePort());
        System.out.println("12 : "+req.getRemoteHost());
        System.out.println("13 : "+req.getServerPort());
        System.out.println("14 : "+req.getServerName());
        System.out.println("15 : "+req.getMethod());
    }
}
