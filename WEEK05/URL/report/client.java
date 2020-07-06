import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {

        // URL url = new URL("http://www.google.com");
        URL url = new URL("http://localhost/HelloServlet");

        ObjectInputStream in = new ObjectInputStream(url.openStream());
        ArrayList<Person> persons = (ArrayList) in.readObject();

        for (Person i : persons)
            System.out.println(i.getName() + " : " + i.getAge());
    }
}