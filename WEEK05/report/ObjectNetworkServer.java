package REPORTS.WEEK05.report;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ObjectNetworkServer 20143174 - 웹서비스 프로그래밍 5주차 두 번째 과제
 * 
 * 소켓통신을 통한 실시간 객체 수신 및 역직렬화 (Client)
 */
public class ObjectNetworkServer {

    public static void main(String[] args) throws Exception {

        Person obj = new Person("jay/940907-1");

        ServerSocket host = new ServerSocket(1000);
        System.out.println("waiting.");

        Socket guest = host.accept();
        System.out.println("connected.");

        ObjectOutputStream out = new ObjectOutputStream(guest.getOutputStream());
        // 연결된 guest 소켓으로 바로 객체를 전달하는 스트림 생성

        out.writeObject(obj);
        // 스트림에 obj(Person)객체를 직렬화하여 저장
        out.flush();

        System.out.println("Completed.");

        out.close();
        guest.close();
        host.close();
    }
}