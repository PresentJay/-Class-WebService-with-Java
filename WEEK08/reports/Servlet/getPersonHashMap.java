import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * getPersonHashMap
 */
@WebServlet("/PersonMaker/getHashMap")
public class getPersonHashMap extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ObjectInputStream in = new ObjectInputStream(req.getInputStream());
            ArrayList<Person> persons = (ArrayList) in.readObject();

            HashMap<String, Person> persons2 = new HashMap<String, Person>();
            for (Person p : persons)
                persons2.put(p.getData("name"), p);

            ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());
            out.writeObject(persons2);
            out.flush();
        } catch (Exception e) {
        }
    }
}