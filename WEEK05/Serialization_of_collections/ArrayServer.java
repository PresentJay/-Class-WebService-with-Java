package REPORTS.WEEK05.Serialization_of_collections;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ArrayServer
 */
public class ArrayServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        ArrayList<Person> persons = new ArrayList<Person>();

        Person person1 = new Person("Jubin/980609-2");
        Person person2 = new Person("Jay/940907-1");
        Person person3 = new Person("lala/000214-3");
        Person person4 = new Person("haha/010101-4");

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(persons);
        out.flush();

        socket.close();
        serverSocket.close();
    }
}