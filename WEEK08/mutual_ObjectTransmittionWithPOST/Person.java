import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Person
 */

// The serializable class Person does not declare a static final
// serialVersionUID field of type long
@SuppressWarnings("serial")
public class Person implements Serializable {

    private HashMap<String, String> Data;

    public Person(String str) {
        String parsedData[] = str.split("/");
        this.Data = new HashMap<String, String>();

        for (String i : parsedData) {
            String s[] = i.split("=");
            Data.put(s[0], s[1]);
        }
    }

    // ② Person 객체는 person.txt에 존재하는 5개의 항목에대한 데이터를 읽어올 수 있도록 메서드를
    // 구현하라.(학번,이름,전화번호,주민번호,주소)
    public String getData(String parameter) {
        if (this.Data.containsKey(parameter))
            return parameter + ":" + this.Data.get(parameter);
        else
            return parameter + ":blank";
    }

    // ③ Person객체는 주민번호를 기초로 하여 그 사람의 생년월일 중 년, 월, 일을 각각 읽어올 수 있도록 메서드를 구현하라.
    public int getYear() {
        String jumin = Data.get("jumin");
        int year = Integer.parseInt(jumin.substring(0, 2));
        int gender = Integer.parseInt(jumin.substring(7, 8));

        if (gender == 1 || gender == 2)
            return 1900 + year;
        else
            return 2000 + year;
    }

    public int getMonth() {
        String jumin = Data.get("jumin");
        return Integer.parseInt(jumin.substring(3, 4));
    }

    public int getDay() {
        String jumin = Data.get("jumin");
        return Integer.parseInt(jumin.substring(5, 6));
    }

    // ④ Person객체는 주민번호를 기초로 하여 나이와 성별을 읽어올 수 있도록 메서드를 각각 구현하라.
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - getYear();
    }

    public String getGender() {
        int gender = Integer.parseInt(Data.get("jumin").substring(7, 8));

        if (gender == 1 || gender == 3)
            return "male";
        else
            return "female";
    }
}