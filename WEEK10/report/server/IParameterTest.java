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

    // 과제물 2개 아래 메서드 2개 구현

    @WebMethod
    public String setStrings(ArrayList<String> list);

    @WebMethod
    public String setPerson(Person person);

    // setPersons는 차주 수업내용. (굳이 할 필요는 없음)

    /*
     * @WebMethod public String setPersons(ArrayList<Person> persons);
     */

}