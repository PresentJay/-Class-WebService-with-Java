package REPORTS.WEEK04;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1000);

        InputStream in = socket.getInputStream();
        FileOutputStream out = new FileOutputStream("test2.jpg");

        byte data[] = new byte[1024];
        int size;

        while ((size = in.read(data)) != -1) {
            out.write(data, 0, size);
            out.flush();
        }

        in.close();
        socket.close();
        out.close();

    }
}