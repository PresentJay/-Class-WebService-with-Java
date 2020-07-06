import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
@WebServlet("/PersonManager")
public class PersonManager extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setCharacterEncoding("utf-8");
            req.setCharacterEncoding("utf-8");

            ObjectInputStream in = new ObjectInputStream(req.getInputStream());
            ArrayList<Person> persons = (ArrayList) in.readObject();

            Person person = new Person("name=이도/jumin=880101-1234567");
            persons.add(person);

            ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
            out.writeObject(persons);
            out.flush();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}