import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * PersonReciver
 */
@WebServlet("/PersonReceiver")
public class PersonReceiver extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setCharacterEncoding("utf-8");
            req.setCharacterEncoding("utf-8");

            ObjectInputStream in = new ObjectInputStream(req.getInputStream());
            ArrayList<Person> persons = (ArrayList) in.readObject();

            PrintWriter out = resp.getWriter();
            Person person = (Person) persons.get(0);
            out.println(person.getData("name") + ":" + person.getAge());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}