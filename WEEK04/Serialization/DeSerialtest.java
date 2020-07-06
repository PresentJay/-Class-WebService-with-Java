package REPORTS.WEEK04.Serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * DeSerialtest
 */
public class DeSerialtest {

    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("REPORTS/WEEK04/Person.ser"));
        Person out = (Person) in.readObject();
        System.out.println(out.getAge() + " : " + out.getGender());

        in.close();
    }
}