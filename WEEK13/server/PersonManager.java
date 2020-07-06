package mytest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jws.WebService;

import com.mysql.cj.xdevapi.Result;

@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")

public class PersonManager implements IPersonManager {

    private String url = "jdbc:mysql://localhost/presentjdb?user=presentj&password=1234&serverTimezone=UTC&useSSL=false";

    public int addPerson(Person person) throws Exception {

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String query = "insert into person(name, jumin) values('" + person.getName() + "','" + person.getJumin() + "')";
        statement.execute(query);

        return statement.getUpdateCount();
    }

    public int updatePerson(Person person, int id) throws Exception {

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String query = "update person set name='" + person.getName() + "', jumin='" + person.getJumin() + "' ";
        query += "where id=" + id;

        statement.execute(query);

        return statement.getUpdateCount();

    }

    public ArrayList<Person> searchPerson(String name) throws Exception {

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String query = "select name, jumin from person where name like '" + name + "%'";
        // 성씨만 맞으면 다 찾아오는것까지 (~%)
        ResultSet result = statement.executeQuery(query);

        ArrayList<Person> list = new ArrayList<Person>();

        while (result.next()) {
            Person person = new Person();
            person.setName(result.getString("name"));
            person.setJumin(result.getString("jumin"));
            list.add(person);
        }

        return list;
    }

    // 12-13 과제물

    public int deletePerson(int id) throws Exception {
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String query = "delete from person where id=" + id;

        statement.execute(query);

        return statement.getUpdateCount();
    }

    public int addPersons(ArrayList<Person> persons) throws Exception {

        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String query = "insert into person(name, jumin) values";

        Iterator<Person> i = persons.iterator();
        while (i.hasNext()) {
            Person tmp = i.next();
            query += "('" + tmp.getName() + "', '" + tmp.getJumin() + "')";
            if (i.hasNext())
                query += ", ";
        }
        statement.execute(query);

        return statement.getUpdateCount();
    }
}