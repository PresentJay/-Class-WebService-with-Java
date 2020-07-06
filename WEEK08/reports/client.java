import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {
        String dest = new String("PersonMaker/getHashMap");
        URL url = new URL("http://localhost/" + dest);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        String filename = new String("person.txt");
        ArrayList<Person> persons = new ArrayList<Person>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));
        String str = "";

        while ((str = in.readLine()) != null)
            persons.add(new Person(str));

        ObjectOutputStream out = new ObjectOutputStream(connection.getOutputStream());
        out.writeObject(persons);
        out.flush();
        in.close();

        ObjectInputStream in2 = new ObjectInputStream(connection.getInputStream());
        HashMap<String, Person> persons2 = (HashMap) in2.readObject();

        for (String s : persons2.keySet()) {
            Person p = persons2.get(s);
            System.out.println(p.getData("name") + ", age:" + p.getAge() + ", gender:" + p.getGender());
        }
    }
}