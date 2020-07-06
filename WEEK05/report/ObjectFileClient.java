package REPORTS.WEEK05.report;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ObjectFileClient 20143174 정현재 - 웹서비스 프로그래밍 5주차 첫 번째 과제
 * 
 * 송신받은 파일을 저장하고, 역직렬화 하여 객체 정보를 출력 (Client))
 */
public class ObjectFileClient {
    public static void main(String[] args) throws Exception {

        String path = "REPORTS/WEEK05/getPerson.ser";
        Socket host = new Socket("localhost", 1000);
        // 소켓 연결

        InputStream in = host.getInputStream();
        FileOutputStream out = new FileOutputStream(path);
        byte[] data = new byte[1024];
        int size;
        while ((size = in.read(data)) != -1) {
            out.write(data, 0, size);
            out.flush();
        }
        // 소켓 데이터로부터 파일을 수신하여 path에 생성

        out.close();
        host.close();

        ObjectInputStream obj = new ObjectInputStream(new FileInputStream(path));
        Person person = (Person) obj.readObject();
        // 전송받은 파일로부터 객체를 Person클래스로 역직렬화하여 person 변수에 저장

        System.out.println(person.getName() + " : " + person.getGender() + ", " + person.getAge());
        // person 객체의 역직렬화 확인(멤버함수 사용)

        obj.close();

    }
}