import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client
 */
public class Client {

    public static void main(String[] args) throws Exception {
        /*
         * URL url = new URL("http://localhost/GetServlet?name=kim&age=20"); InputStream
         * in = url.openStream();
         * 
         * byte data[] = new byte[1024]; int size;
         * 
         * while ((size = in.read(data)) != -1) { System.out.println(new String(data, 0,
         * size, "utf-8")); }
         */

        URL url = new URL("http://localhost/PostServlet");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        OutputStream out = connection.getOutputStream();
        String str = "name=kim&age=20";

        byte data[] = str.getBytes();
        out.write(data);
        out.flush();

        InputStream in = connection.getInputStream();
        byte data2[] = new byte[1024];
        int size;

        while ((size = in.read(data2)) != -1) {
            System.out.println(new String(data2, 0, size, "utf-8"));
        }
    }
}