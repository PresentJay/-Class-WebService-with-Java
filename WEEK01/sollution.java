package REPORTS.WEEK01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class sollution {

    public static void main(String[] args) throws IOException {

        HashMap<String, Integer> names = new HashMap<String, Integer>();
        String filepath = "REPORTS/WEEK01/info.txt";

        try {
            FileInputStream file = new FileInputStream(filepath);
            BufferedReader in = new BufferedReader(new InputStreamReader(file));

            String str = "";
            while ((str = in.readLine()) != null) {
                if (names.containsKey(str))
                    names.put(str, names.get(str) + 1);
                else
                    names.put(str, 1);
            }
            in.close();
            for (String s : names.keySet())
                System.out.println(s + " " + names.get(s));

        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }
}