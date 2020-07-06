package REPORTS.WEEK04.report;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws Exception {
        // 소켓 생성
        Socket socket = new Socket("localhost", 1000);

        String answer;
        Scanner s = new Scanner(System.in);
        System.out.print("서버로부터 전송받을 파일의 이름을 입력하세요(확장자까지)\n >> ");
        answer = s.nextLine();

        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            FileOutputStream out = new FileOutputStream(answer);

            byte data[] = new byte[1024];
            int size;

            while ((size = in.read(data)) != -1) {
                out.write(data, 0, size);
                out.flush();
            }
            out.close();
        } catch (Exception e) {
            System.out.println("문제가 발생했습니다.");
        }

        socket.close();
        s.close();
    }
}