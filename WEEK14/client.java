import java.util.ArrayList;

/**
 * client
 */
public class client {

    public static void main(String[] args) throws Exception {
        SOAPManager manager = new SOAPManager();
        manager.setNamespace("http://webservice.class.inje.ac.kr");
        manager.setURL("http://203.241.251.177:8080/parameter/ParamTest");

        // 1 : parameter 없는 함수 호출
        /* manager.setOperation("getStrings"); */

        // 2 : ArrayList parameter 함수 호출
        /*
         * manager.setOperation("setStrings"); ArrayList<String> list = new
         * ArrayList<String>(); list.add("kim"); list.add("lee"); list.add("jeong");
         * manager.setParameter("0", list);
         */

        // 3 : Object parameter 함수 호출
        /*
         * manager.setOperation("setPerson"); Person person = new Person(); person.name
         * = "kim"; person.jumin = "010101-1234567"; manager.setParameter("0", person);
         */

        // 4 : ArrayList<Object> parameter 함수 호출 (ArrayList의 구분이 안 되서 함수명 변경)
        /*
         * manager.setOperation("setPersons"); ArrayList<Person> persons = new
         * ArrayList<Person>(); Person person = new Person(); person.name = "Jeong";
         * person.jumin = "020202-1234567"; Person person2 = new Person(); person2.name
         * = "kim"; person2.jumin = "010101-1234567"; persons.add(person);
         * persons.add(person2); manager.setParameterArrayObject("0", persons);
         */

        /*
         * manager.setOperation("setABC"); ArrayList<Person> persons = new
         * ArrayList<Person>(); Person person = new Person(); person.name = "Jeong";
         * person.jumin = "020202-1234567"; Person person2 = new Person(); person2.name
         * = "kim"; person2.jumin = "010101-1234567"; persons.add(person);
         * persons.add(person2); manager.setParameterArrayObject("0", persons);
         * 
         * manager.setParameter("1", "정현재");
         * 
         * ArrayList<String> list = new ArrayList<String>(); list.add("kim");
         * list.add("lee"); list.add("jeong"); manager.setParameter("2", list);
         */

        // << request message 전체 출력하기 >>
        // System.out.println(manager.getRequestMessage());

        // 5 : 결과값 파싱해보기 1
        /*
         * manager.setOperation("getStrings"); ArrayList<DataObject> objects =
         * manager.send();
         * 
         * System.out.println(objects.size());
         * 
         * for (DataObject i : objects) System.out.println(i.getValue("return"));
         */

        // 6 : 결과값 파싱해보기 2
        /*
         * manager.setOperation("getPerson"); ArrayList<DataObject> objects =
         * manager.send();
         * 
         * System.out.println(objects.size());
         * 
         * for (DataObject i : objects) { System.out.println(i.getValue("name"));
         * System.out.println(i.getValue("jumin")); }
         */

        // 7: 결과값 파싱해보기 3
        /*
         * manager.setOperation("getPersons"); ArrayList<DataObject> objects =
         * manager.send();
         * 
         * System.out.println(objects.size());
         * 
         * for (DataObject i : objects) { System.out.println(i.getValue("name"));
         * System.out.println(i.getValue("jumin")); }
         */

        manager.setOperation("setABC");
        ArrayList<Person> persons = new ArrayList<Person>();
        Person person = new Person();
        person.name = "Jeong";
        person.jumin = "020202-1234567";
        Person person2 = new Person();
        person2.name = "kim";
        person2.jumin = "010101-1234567";
        persons.add(person);
        persons.add(person2);
        manager.setParameterArrayObject("0", persons);

        manager.setParameter("1", "정현재");

        ArrayList<String> list = new ArrayList<String>();
        list.add("kim");
        list.add("lee");
        list.add("jeong");
        manager.setParameter("2", list);

        ArrayList<DataObject> objects = manager.send();

        System.out.println(objects.size());

        for (DataObject i : objects) {
            System.out.println(i.getValue("name"));
            System.out.println(i.getValue("jumin"));
        }

        // << response message 전체 출력하기 >>
        // System.out.println(manager.getResponseMessage());

    }
}