package lk.ijse.gdse.hello.api;

import jakarta.json.*;

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
        /*-------------without json library-----------*/

    /*    BufferedReader reader = req.getReader();
        String line;
        String json = "";
        while ((line = reader.readLine()) !=null){
            json += line + "\n";
        }
            System.out.println(json);*/

/*        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject =reader.readObject();
        System.out.println(jsonObject);


        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        JsonObject addressJsonObject = jsonObject.getJsonObject("address");

        int no = addressJsonObject.getInt("no");
        String street =addressJsonObject.getString("street");
        String city =addressJsonObject.getString("city");

        JsonArray contactsJsonArray =jsonObject.getJsonArray("contacts");
        String firstContact =contactsJsonArray.getString(0);
        String secondContact =contactsJsonArray.getString(1);

        System.out.println(id);
        System.out.println(name);
        System.out.println(addressJsonObject);
        System.out.println("no" + no + "street" + street + "city" +city);
        System.out.println("first Contact" +firstContact);
        System.out.println("Second Contact" +secondContact);*/


        /*----------using JSON-P library----------*/

//        JsonReader jsonReader = Json.createReader(req.getReader());
//        JsonObject jsonObject = jsonReader.readObject();
//        System.out.println(jsonObject);

        /*-----------write json-----------*/

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
