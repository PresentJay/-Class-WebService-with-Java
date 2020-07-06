package mytest;

import javax.jws.WebService;

/**
 * helloworld
 */

@WebService(targetNamespace = "http://webservice.class.inje.ac.kr")
public class helloworld implements ihelloworld {
    public String sayHello(String str) {
        return str + " from Web service";
    }

    public int plus(int a, int b) {
        return a + b;
    }
}