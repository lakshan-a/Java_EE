package lk.ijse.gdse.hello.api;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =req.getParameter("id");
        String name =req.getParameter("name");
        String address =req.getParameter("address");
        String salary =req.getParameter("salary");
        Connection connection = null;

        System.out.printf("id=%s, name=%s, address=%s, salary=%s\n ", id, name, address,salary);


//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection= DriverManager.getConnection(url,username,password);
//            PreparedStatement stm =connection.prepareStatement("INSERT INTO customer(id,name,address,salary) VALUE (?,?,?,?)");
//
//            stm.setString(1,id);
//            stm.setString(2,name);
//            stm.setString(3,address);
//            stm.setString(4,salary);
//            stm.executeUpdate();
//
//
//        } catch (ClassNotFoundException | SQLException e) {
////            e.printStackTrace();
//            throw new RuntimeException(e);
//
//        }finally {
//            if (connection !=null){
//                try{
//                    connection.close();
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
//            }
//        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println();


    }
}
