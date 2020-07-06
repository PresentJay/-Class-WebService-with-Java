package mytest;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")
@SOAPBinding(style = Style.RPC)
/**
 * IParameterTest
 */
public interface IParameterTest {

    @WebMethod
    public ArrayList<String> getStrings();

    @WebMethod
    public Person getPerson();

    @WebMethod
    public ArrayList<Person> getPersons();

    @WebMethod
    public ArrayList<String> setStrings(ArrayList<String> list);

    @WebMethod
    public String setPerson(Person person);

    @WebMethod
    public ArrayList<Person> setPersons(ArrayList<Person> persons);

    @WebMethod
    public ArrayList<Person> setABC(ArrayList<Person> persons, String str, ArrayList<String> strs);
}