package REPORTS.WEEK04;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socket = serverSocket.accept();

        FileInputStream in = new FileInputStream("test.jpg");
        OutputStream out = socket.getOutputStream();

        byte data[] = new byte[1024];
        int size;
        while ((size = in.read(data)) != -1) {
            out.write(data);
            out.flush();
        }

        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }
}