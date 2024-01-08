package lk.ijse.gdse.hello.api;

import com.sun.xml.internal.ws.transport.http.HttpAdapter;
import jakarta.json.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "customer" , urlPatterns = "/customer", loadOnStartup = 1, initParams = {
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
        /*ServletConfig is used to get configuration information such as database url, mysql username and password*/
        ServletConfig sc = getServletConfig();
        username = sc.getInitParameter("username");
        password = sc.getInitParameter("password");
        url = sc.getInitParameter("url");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");

        /*catch request parameter as a String*/
        /*String id = req.getParameter("id");
        String name = req.getParameter("name");
        String address = req.getParameter("address");*/

        System.out.printf("id=%s, name=%s, address=%s\n", id,name,address);

        /*create a database connection and save data in database*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stm = connection.prepareStatement("INSERT INTO customer(id, name, address) VALUES (?,?,?)");

            stm.setString(1,id);
            stm.setString(2, name);
            stm.setString(3, address);

            stm.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection !=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;

        /*create a database connection and fetch data in database*/
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,username,password);
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet rst = stm.executeQuery();

            /*String jsonArray = "";*/

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                System.out.printf("id=%s, name=%s, address=%s\n",id,name,address);

                /*String customer = String.format("id=%s, name=%s, address=%s\n", id, name, address);
                writer.write(customer); // write all customers as the text in response*/

                /*Example for Json object format: {"id":C001, "name":Kasun, "address":Galle}*/
                /*String jsonObject = "{ \"id\": \"" + id + "\"," + "\"name\":\""+ name+ "\"," + "\"address\":\"" + address + "\"}"; //convert one customer record to JSON object format
                jsonArray += jsonObject + ",";*/

                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("id",id);
                objectBuilder.add("name",name);
                objectBuilder.add("address",address);
                JsonObject customerJsonObject = objectBuilder.build(); //create JSON objects for each customer


                arrayBuilder.add(customerJsonObject); // add each customer into JSON array
            }

            /*jsonArray = "[" + jsonArray.substring(0,jsonArray.length()-1) + "]"; //create JSON array format to add all customers

            resp.getWriter().write(jsonArray); //write JSON array format in response*/

            JsonArray jsonArray = arrayBuilder.build();
            resp.getWriter().write(jsonArray.toString()); //write JSON array in response

            resp.setContentType("application/json"); //set the MIME type of the content of the response (Thus, add response header called "Content-Type")

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(connection !=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
