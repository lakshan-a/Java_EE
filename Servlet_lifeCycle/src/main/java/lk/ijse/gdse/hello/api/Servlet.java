package lk.ijse.gdse.hello.api;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {

    static {
        System.out.println("Servlet is being  ");
    }

    public Servlet(){
        System.out.println("Servlet: Constructor");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("initServletConfig");
        super.init(config);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("int()");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
    }

    @Override
    public void destroy() {
        System.out.println("Servlet is about destroy");
    }
}
