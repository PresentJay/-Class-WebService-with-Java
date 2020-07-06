package REPORTS.WEEK05.URL;

import java.io.InputStream;
import java.net.URL;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {

        // URL url = new URL("http://www.google.com");
        URL url = new URL("http://localhost/HelloServlet");

        InputStream in = url.openStream();

        byte[] data = new byte[1024];
        int size;

        while ((size = in.read(data)) != -1) {
            System.out.println(new String(data, 0, size, "utf-8"));
        }
    }
}