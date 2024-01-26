package lk.ijse.gdse66.hello.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse66.hello.dto.CustomerDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

@WebServlet(name = "Customer", urlPatterns = "/customers", loadOnStartup = 1)

public class CustomerServlet extends HttpServlet {
    private String username;
    private String password;
    private String url;

    BasicDataSource pool;

    @Override
    public void init() throws ServletException {

        ServletContext sc = getServletContext();
        pool = (BasicDataSource) sc.getAttribute("dbcp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = null;


        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();

        /* do some validation */
        if(id==null || !id.matches("C\\d{3}")){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID is empty or invalid");
            return;
        } else if (name == null || !name.matches("[A-Za-z ]+")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Name is empty or invalid");
            return;
        } else if (address == null || address.length() < 3) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Address is empty or invalid");
            return;
        }

        System.out.printf("id=%s, name=%s, address=%s\n", id,name,address);

        /*create a database connection and save data in database*/
        try (Connection connection =pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("INSERT INTO customer(id, name, address) VALUES (?,?,?)");

            stm.setString(1,id);
            stm.setString(2, name);
            stm.setString(3, address);

            if (stm.executeUpdate() != 0) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("Added customer successfully");
            }else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save the customer");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        /*finally {
            if(connection !=null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*String action = req.getParameter("action");
        if(action ==null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "action is empty");
        } else-if(action.equalsIgnoreCase("GETALL")){
            getAllCustomers();
        } else if (action.equalsIgnoreCase("GETONE")) {
            getCustomerById();
        }*/

//        resp.addHeader("Access-Control-Allow-Origin","*");


        /*create a database connection and fetch data in database*/
        try (Connection connection =pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM customer");
            ResultSet rst = stm.executeQuery();

            ArrayList<CustomerDTO> customerList = new ArrayList<>();

            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                System.out.printf("id=%s, name=%s, address=%s\n",id,name,address);

                customerList.add(new CustomerDTO(id, name, address));
            }

            resp.setContentType("application/json"); //set the MIME type of the content of the response (Thus, add response header called "Content-Type")

            Jsonb jsonb = JsonbBuilder.create();
            jsonb.toJson(customerList,resp.getWriter());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        resp.addHeader("Access-Control-Allow-Origin","*");

        String id = req.getParameter("id");

        try (Connection connection =pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("DELETE FROM customer WHERE id=?");
            stm.setString(1,id);

            if(stm.executeUpdate() != 0){
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete the customer!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.addHeader("Access-Control-Allow-Origin","*");

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);
        String id = customerDTO.getId();
        String name = customerDTO.getName();
        String address = customerDTO.getAddress();

        System.out.printf("id=%s, name=%s, address=%s\n", id,name,address);

        try (Connection connection =pool.getConnection()){
            PreparedStatement stm = connection.prepareStatement("UPDATE customer SET name=?, address=? WHERE id=?");

            stm.setString(1, name);
            stm.setString(2, address);
            stm.setString(3,id);

            if (stm.executeUpdate() != 0) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update the customer");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*@Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.addHeader("Access-Control-Allow-Methods", "DELETE, PUT");
    }*/
}
