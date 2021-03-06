package REPORTS.WEEK04.Serialization;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Person
 */

@SuppressWarnings("serial")
public class Person implements Serializable {
    private String name;
    private String number;

    public Person(String str) {
        String parsedData[] = str.split("/");
        this.name = parsedData[0];
        this.number = parsedData[1];
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - getYear();
    }

    public String getGender() {
        int gender = Integer.parseInt(number.substring(7, 8));

        if (gender == 1 || gender == 3)
            return "male";
        else
            return "female";
    }

    private int getYear() {
        int year = Integer.parseInt(number.substring(0, 2));
        int gender = Integer.parseInt(number.substring(7, 8));

        if (gender == 1 || gender == 2)
            return 1900 + year;
        else
            return 2000 + year;
    }
}