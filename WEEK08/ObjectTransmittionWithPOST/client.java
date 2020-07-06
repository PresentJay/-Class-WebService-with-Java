import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost/PersonReceiver");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        ArrayList<Person> persons = new ArrayList<Person>();
        Person person1 = new Person("name=이방원/jumin=010101-3456789");
        Person person2 = new Person("name=이성계/jumin=950505-1234567");
        persons.add(person1);
        persons.add(person2);

        ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
        out.writeObject(persons);
        out.flush();

        InputStream in = connection.getInputStream();
        byte data[] = new byte[1024];
        int size;

        while ((size = in.read(data)) != -1)
            System.out.println(new String(data, 0, size, "utf-8"));
    }
}