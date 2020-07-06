import java.io.*;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet("/HelloServlet")

public class ObjectServlet extends HttpServlet {
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");

        ArrayList<Person> persons = new ArrayList<Person>();

        Person person1 = new Person("Jubin/980609-2");
        Person person2 = new Person("Jay/940907-1");
        Person person3 = new Person("lala/000214-3");
        Person person4 = new Person("haha/010101-4");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);

        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        out.writeObject(persons);
        out.flush();

    }
}