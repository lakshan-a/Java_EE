package lk.ijse.gdse.hello.api;

import jakarta.json.*;
import jdk.nashorn.internal.runtime.JSONListAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//@WebServlet(name = "Hello_servlet" , value = "/" , loadOnStartup = 1)

@WebServlet(urlPatterns = "/test")
public class Form_Action extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "12345");
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT *from customer");
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",set.getString(1));
                objectBuilder.add("name",set.getString(2));
                objectBuilder.add("address",set.getString(3));

                arrayBuilder.add(objectBuilder.build());
            }
            resp.setContentType("application/json");
            resp.getWriter().write(arrayBuilder.build().toString());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "12345");
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address) VALUES(?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);

            stn.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//        String name = req.getParameter("id");
//        String id = req.getParameter("name");
//        String address = req.getParameter("address");

//        System.out.println(id);
//        System.out.println(name);
//        System.out.println(address);

        //Deta Get using JSon Deta Reqest
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
//        String id = jsonObject.getString("id");
//        String name = jsonObject.getString("name");
//        String address = jsonObject.getString("address");
//        System.out.println(id);
//        System.out.println(name);
//        System.out.println(address);

//        Deta Responses

//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        objectBuilder.add("id","C001");
//        objectBuilder.add("name","Lakshan");
//        objectBuilder.add("address","Matara");
//        JsonObject jsonObject = objectBuilder.build();
//        resp.getWriter().write(jsonObject.toString());


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        Connection connection = null;
//
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "12345");
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address) VALUES(?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);

            stn.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
