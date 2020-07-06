import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost/parameter/ParamTest");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "text/xml; charset=\"utf-8\"");
        connection.setRequestProperty("Target-Host", "http://localhost/hello/Hello");

        OutputStream out = connection.getOutputStream();
        // FileInputStream filein = new FileInputStream("requestMessage.xml");
        String message = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
        message = message + "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"";
        message = message + " xmlns:ns0=\"http://webservice.class.inje.ac.kr\"";
        message = message + " env:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">";
        message = message + "<env:Header/><env:Body><ns0:getPersons>";
        // message = message + "<arg0>클라이언트에서 보내는 메시지</arg0>";
        message = message + "</ns0:getPersons></env:Body></env:Envelope>";

        byte data[] = message.getBytes("utf-8");
        int size;

        out.write(data, 0, data.length);
        out.flush();

        /*
         * while((size=filein.read(data))!=-1){ out.write(data, 0, size); out.flush(); }
         */

        out.close();

        InputStream in = connection.getInputStream();

        while ((size = in.read(data)) != -1)
            System.out.println(new String(data, 0, size, "utf-8"));
    }
}