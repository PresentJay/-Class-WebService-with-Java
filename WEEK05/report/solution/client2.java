package REPORTS.WEEK05.report.solution;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * client2
 */
public class client2 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 1000);

        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        Person person = (Person) socketIn.readObject();

        System.out.println(person.getName() + " : " + person.getAge());

        socket.close();
    }
}