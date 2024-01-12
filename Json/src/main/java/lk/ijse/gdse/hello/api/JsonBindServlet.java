package lk.ijse.gdse.hello.api;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.hello.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "jsonLibraries", urlPatterns = "/jsonB")
public class JsonBindServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*java Object ------>Json*/
     /*   Student s1 = new Student("S001","lakshan",18);
        Student s2 = new Student("S002","kalshan",27);

        ArrayList<Student> students = new ArrayList<>();

        students.add(s1);
        students.add(s2);

        Jsonb jsonb =JsonbBuilder.create();*/




        Jsonb jsonb =JsonbBuilder.create();

        ArrayList<Student> studentList = jsonb.fromJson(req.getReader(),new ArrayList<Student>(){}.getClass().getGenericSuperclass());
        System.out.println(studentList);
        











    }
}
