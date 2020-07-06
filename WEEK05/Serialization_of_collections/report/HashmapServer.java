package REPORTS.WEEK05.Serialization_of_collections.report;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * HashmapServer
 */
public class HashmapServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        HashMap<String, Person> obj = new HashMap<String, Person>();

        Person person1 = new Person("Jubin/980609-2");
        Person person2 = new Person("Jay/940907-1");
        Person person3 = new Person("lala/000214-3");
        Person person4 = new Person("haha/010101-4");
        obj.put(person1.getName(), person1);
        obj.put(person2.getName(), person2);
        obj.put(person3.getName(), person3);
        obj.put(person4.getName(), person4);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(obj);
        out.flush();

        socket.close();
        serverSocket.close();
    }
}