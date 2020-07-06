package REPORTS.WEEK05.report.solution;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server2
 */
public class server2 {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        // Person 객체를 생성하고 클라이언트로 객체 전송(직렬화)
        Person person = new Person("Jay/940907-1");
        ObjectOutputStream socketOut = new ObjectOutputStream(socket.getOutputStream());
        socketOut.writeObject(person);
        socketOut.flush();

        socket.close();
        serverSocket.close();
    }
}