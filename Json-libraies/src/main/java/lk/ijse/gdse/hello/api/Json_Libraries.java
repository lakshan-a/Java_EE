package lk.ijse.gdse.hello.api;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "jsonLibraries", urlPatterns = "/json")
public class Json_Libraries extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* ----------------------- Read JSON--------------------*/
        /* without json libraries */
        /*BufferedReader reader = req.getReader();
        String line;
        String json = "";
        while((line = reader.readLine()) != null ){
            json += line + "\n";
        }
        System.out.println(json);*/


        /* using JSON-P library */
        /*JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        System.out.println(jsonObject);*/

        /* ----------------------- Write JSON--------------------*/

        /* using JSON-P library */
        /*JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("id", "C001");
        objectBuilder.add("name", "Kasun");
        objectBuilder.add("address", "Galle");
        JsonObject jsonObject = objectBuilder.build();*/

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", "C001")
                .add("name", "Kasun")
                .add("address", "Galle").build();

        resp.getWriter().write(jsonObject.toString());
    }
}
