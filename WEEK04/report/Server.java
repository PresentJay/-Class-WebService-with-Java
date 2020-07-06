package REPORTS.WEEK04.report;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) throws Exception {

        // 서버소켓 생성
        ServerSocket serverSocket = new ServerSocket(1000);
        Scanner s = new Scanner(System.in);
        String answer;

        System.out.print("서버로 올릴 파일명을 입력하세요(확장자까지)\n >> ");
        answer = s.nextLine();

        try {
            FileInputStream in = new FileInputStream(answer);

            // 접속 대기
            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            byte data[] = new byte[1024];
            int size;

            while ((size = in.read(data)) != -1) {
                out.write(data, 0, size);
                out.flush();
            }

            // 메모리 누수방지를 위해 사용한 오브젝트들을 close해줍니다.
            socket.close();

            in.close();
            out.close();

            System.out.println("파일 전송이 완료되었습니다.");

        } catch (Exception e) {
            System.out.println("문제가 발생했습니다.");
        }

        serverSocket.close();
        s.close();
    }
}