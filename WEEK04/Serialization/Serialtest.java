package REPORTS.WEEK04.Serialization;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * Serialtest
 */
public class Serialtest {

    public static void main(String[] args) throws Exception {

        Person person = new Person("Kim/010101-3");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("REPORTS/WEEK04/Person.ser"));
        out.writeObject(person);

        out.close();
    }
}