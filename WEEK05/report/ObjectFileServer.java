package REPORTS.WEEK05.report;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ObjectFileServer 20143174 정현재 - 웹서비스 프로그래밍 5주차 첫 번째 과제
 * 
 * Person 클래스의 객체를 직렬화한 파일 송신 (Server)
 */

public class ObjectFileServer {

    public static void main(String[] args) throws Exception {

        String path = "REPORTS/WEEK05/Person.ser";
        String context = "jay/940907-1";

        // 1단계 : 객체 파일을 생성

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        Person obj = new Person(context);
        out.writeObject(obj);
        // obj 객체를 직렬화하여 path에 저장. (내용은 context)
        out.close();

        // 2단계 : 생성된 파일을 네트워크로 전송

        ServerSocket host = new ServerSocket(1000);
        System.out.println("waiting.");
        // 서버 소켓 생성

        Socket guest = host.accept();
        System.out.println("connected : " + path);
        // 클라이언트 연결

        FileInputStream file = new FileInputStream(path);
        OutputStream stream = guest.getOutputStream();
        byte[] data = new byte[1024];
        int size;
        while ((size = file.read(data)) != -1) {
            stream.write(data);
            stream.flush();
        }
        // 파일을 stream을 통해 byte로 전송함

        System.out.println("completed : " + context);

        file.close();
        guest.close();
        host.close();
    }
}