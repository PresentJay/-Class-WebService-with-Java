package mytest;

import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")
@SOAPBinding(style = Style.RPC)
public interface IPersonManager {

    @WebMethod
    public int addPerson(Person person) throws Exception;

    @WebMethod
    public int updatePerson(Person person, int id) throws Exception;

    @WebMethod
    public ArrayList<Person> searchPerson(String name) throws Exception;

    // 12-13 과제물

    @WebMethod
    public int deletePerson(int id) throws Exception;

    @WebMethod
    public int addPersons(ArrayList<Person> persons) throws Exception;
}