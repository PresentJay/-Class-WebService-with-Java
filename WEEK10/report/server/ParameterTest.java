package mytest;

import java.util.ArrayList;

import javax.jws.WebService;

@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")
/**
 * ParameterTest
 */
public class ParameterTest implements IParameterTest {

    public ArrayList<String> getStrings() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("inje");
        list.add("university");
        list.add("computer");
        list.add("engineering");

        return list;
    }

    public Person getPerson() {
        Person person = new Person("name=이방원/jumin=010101-1234567");
        return person;
    }

    public ArrayList<Person> getPersons() {
        ArrayList<Person> persons = new ArrayList<Person>();
        Person person1 = new Person("name=이방원/jumin=010101-1234567");
        Person person2 = new Person("name=이성계/jumin=010101-2234567");

        persons.add(person1);
        persons.add(person2);

        return persons;
    }

    public String setStrings(ArrayList<String> list) {
        String message = "입력값 : [";

        for (String s : list)
            message += s.equals(list.get(0)) ? s : "," + s;

        return message + "], 첫번째 값 : " + list.get(0);
    }

    public String setPerson(Person person) {
        return "주민번호 앞자리 : " + person.getJumin().split("-")[0] + ", 이름 : " + person.getName();
    }

}