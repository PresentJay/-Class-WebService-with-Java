package REPORTS.WEEK05.report.solution;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * client1
 */
public class client1 {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 1000);

        // 서버에서 전송하는 파일을 로컬에 파일로 저장
        InputStream socketIn = socket.getInputStream();
        FileOutputStream fileOut = new FileOutputStream("Person2.ser");

        byte data[] = new byte[1024];
        int size;

        while ((size = socketIn.read(data)) != -1) {
            fileOut.write(data, 0, size);
            fileOut.flush();
        }

        fileOut.close();

        // 직렬화된 파일에서 읽어 역직렬화

        ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("Person2.ser"));
        Person person = (Person) objectIn.readObject();

        System.out.println(person.getName() + " : " + person.getAge());

        objectIn.close();
        socket.close();

    }
}