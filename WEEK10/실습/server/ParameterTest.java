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
}