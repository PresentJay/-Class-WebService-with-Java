package mytest;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

// 그냥 URI 형태로 만들어보는거 (실제 웹 서비스랑은 상관X)
@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")
@SOAPBinding(style = Style.RPC)
/**
 * ihelloworld
 */
public interface ihelloworld {

    @WebMethod
    public String sayHello(String str);

    @WebMethod
    public int plus(int a, int b);
}
