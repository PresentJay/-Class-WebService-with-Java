package REPORTS.WEEK04;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Filecopy
 */
public class Filecopy {
    public static void main(String[] args) throws Exception {
        String filename = "test.jpg";
        String outputName = "test2.jpg";

        FileInputStream in = new FileInputStream(filename);
        FileOutputStream out = new FileOutputStream(outputName);

        byte data[] = new byte[1024];
        int size;

        while ((size = in.read(data)) != -1) {
            out.write(data, 0, size);
            out.flush();
        }

        in.close();
        out.close();
    }
}