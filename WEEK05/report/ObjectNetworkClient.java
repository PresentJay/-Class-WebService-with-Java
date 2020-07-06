package REPORTS.WEEK05.report;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * ObjectNetworkClient 20143174 정현재 - 웹서비스 프로그래밍 5주차 두 번째 과제
 * 
 * 소켓통신을 통한 실시간 객체 직렬화 송신 (Server)
 */
public class ObjectNetworkClient {

    public static void main(String[] args) throws Exception {

        Socket host = new Socket("localhost", 1000);
        // server와 연결하는 소켓 생성
        ObjectInputStream in = new ObjectInputStream(host.getInputStream());
        // 연결된 소켓으로부터 객체 데이터를 바로 전송받는 stream 생성
        Person obj = (Person) in.readObject();
        // 전송받은 객체를 Person클래스로 역직렬화

        System.out.println(obj.getName() + " : " + obj.getGender() + ", " + obj.getAge());
        // 객체 역직렬화 결과 확인 (클래스 멤버함수 사용)

        in.close();
        host.close();
    }
}