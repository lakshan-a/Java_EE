package lk.ijse.gdse.hello.api;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jsonLibraries", urlPatterns = "/json")
public class Libraries extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        objectBuilder.add("id","C001");
        objectBuilder.add("name","Lakshan");

        JsonObjectBuilder addressObjectBulider =Json.createObjectBuilder();
        addressObjectBulider.add("no",10);
        addressObjectBulider.add("street","main street");
        addressObjectBulider.add("city","Galle");

        objectBuilder.add("address",addressObjectBulider);

        JsonArrayBuilder contactArrayBuilder =Json.createArrayBuilder();
        contactArrayBuilder.add("0713988628");
        contactArrayBuilder.add("0716606719");

        objectBuilder.add("contact",contactArrayBuilder);

        resp.getWriter().write(objectBuilder.build().toString());
    }
}
