package lk.ijse.gdse.hello.api;

import jakarta.json.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import lk.ijse.gdse.hello.dto.CustomerDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

//@WebServlet(name = "Hello_servlet" , value = "/" , loadOnStartup = 1)
//
@WebServlet(urlPatterns = "/test")
public class CustomerServlet extends HttpServlet {
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

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDto customerDto =jsonb.fromJson(req.getReader(),CustomerDto.class);
        String id = customerDto.getId();
        String name = customerDto.getName();
        String address = customerDto.getAddress();

//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");

        if (id == null || !id.matches("c\\d{3}")){
            resp.getWriter().write("id is empty or id is invalid");
            return;
        }else if(name == null || !name.matches("[A-Za-z]+")){
            resp.getWriter().write("name is empty or name is invalid");
            return;
        }else if(address == null || !address.matches("[A-Za-z]+")){
            resp.getWriter().write("address is empty or address is invalid");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "12345");
            PreparedStatement stn = connection.prepareStatement("INSERT INTO customer(id,name,address) VALUES(?,?,?)");

            stn.setString(1,id);
            stn.setString(2,name);
            stn.setString(3,address);

            if (stn.executeUpdate() !=0){
                resp.getWriter().write("Customer Save Success");
            }else {
                resp.getWriter().write("Failed to save Customer");
            }

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
        Connection connection = null;

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDto customerDto =jsonb.fromJson(req.getReader(),CustomerDto.class);
        String id = customerDto.getId();

        System.out.printf("id=%s\n", id);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello", "root", "12345");
            PreparedStatement stn = connection.prepareStatement("DELETE FROM customer WHERE id=?");

            stn.setString(1, id);

            if (stn.executeUpdate() !=0){
                resp.getWriter().write("Customer Delete Success");
            }else {
                resp.getWriter().write("Failed to Delete Customer");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String address = req.getParameter("address");

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDto customerDto =jsonb.fromJson(req.getReader(),CustomerDto.class);
        String id = customerDto.getId();
        String name = customerDto.getName();
        String address = customerDto.getAddress();



        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =   DriverManager.getConnection("jdbc:mysql://localhost:3306/gdse66_hello","root","12345");
            PreparedStatement stn = connection.prepareStatement(" UPDATE customer SET name=?,address=? WHERE id=?");


            stn.setString(1,name);
            stn.setString(2,address);
            stn.setString(3,id);

            if (stn.executeUpdate() !=0){
                resp.getWriter().write("Customer Updated Success");
            }else {
                resp.getWriter().write("Failed to Updated Customer");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection !=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
