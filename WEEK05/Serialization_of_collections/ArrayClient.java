package REPORTS.WEEK05.Serialization_of_collections;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ArrayClient
 */
public class ArrayClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1000);

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        ArrayList<Person> persons = (ArrayList) in.readObject();

        for (Person i : persons)
            System.out.println(i.getName() + " : " + i.getAge());

        socket.close();
    }
}