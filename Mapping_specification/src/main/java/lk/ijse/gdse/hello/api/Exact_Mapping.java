package lk.ijse.gdse.hello.api;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Exact_Mapping" , value = "/hello")


public class Exact_Mapping extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Hello I am Exact Mapping (/**) </h1>");

        System.out.println(req.getContextPath());
        System.out.println(req.getPathInfo());
        System.out.println(req.getServletPath());
        System.out.println(req.getPathTranslated());
        System.out.println(req.getQueryString());
        System.out.println(req.getRequestURI());
        System.out.println(req.getRequestURL());
        System.out.println(req.getProtocol());
        System.out.println(req.getScheme());
        System.out.println(req.getRemoteAddr());
        System.out.println(req.getRemotePort());
        System.out.println(req.getRemoteHost());
        System.out.println(req.getServerPort());
        System.out.println(req.getServerName());
        System.out.println(req.getMethod());
    }
}
