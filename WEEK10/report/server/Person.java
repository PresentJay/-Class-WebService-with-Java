package mytest;

/**
 * Person
 */
public class Person {

    private String name;
    private String jumin;

    public Person(String string) {
        String parsedData[] = string.split("/");
        for (String i : parsedData) {
            String s[] = i.split("=");
            if (s[0].equals("name"))
                this.name = s[1];

            if (s[0].equals("jumin"))
                this.jumin = s[1];
        }
    }

    public Person() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJumin(String jumin) {
        this.jumin = jumin;
    }

    public String getName() {
        return name;
    }

    public String getJumin() {
        return jumin;
    }
}