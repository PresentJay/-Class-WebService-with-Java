package REPORTS.WEEK05.Serialization_of_collections.report;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * HashmapClient
 */
public class HashmapClient {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1000);

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

        HashMap<String, Person> obj = (HashMap) in.readObject();

        for (String s : obj.keySet())
            System.out.println(s + ":" + obj.get(s).getAge());

        socket.close();
    }
}