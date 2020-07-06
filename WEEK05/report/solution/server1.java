package REPORTS.WEEK05.report.solution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * server1
 */
public class server1 {

    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        // Person 객체를 생성하고 파일로 직렬화
        Person person = new Person("Jay/940907-1");
        ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream("Person.ser"));
        fileOut.writeObject(person);
        fileOut.close();

        // 직렬화된 객체를 클라이언트로 전송
        FileInputStream fileIn = new FileInputStream("Person.ser");
        OutputStream socketOut = socket.getOutputStream();

        byte[] data = new byte[1024];
        int size;

        while ((size = fileIn.read(data)) != -1) {
            socketOut.write(data, 0, size);
            socketOut.flush();
        }

        fileIn.close();
        socket.close();
        serverSocket.close();
    }
}