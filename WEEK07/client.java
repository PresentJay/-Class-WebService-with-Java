import java.io.IOError;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost/postservlet");
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

        while ((size = in.read(data2)) != -1)
            System.out.println(new String(data2, 0, size));
    }

}