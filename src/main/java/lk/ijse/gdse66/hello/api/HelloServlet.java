package lk.ijse.gdse66.hello.api;

//java EE use karanwam danna oni package ekaka
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        request eka awe kohomada balaganne remot is clinet
        System.out.println("Incoming request: " +req.getRemoteAddr());
        resp.getWriter().println("Hello I From the Hello Server");
    }
}
