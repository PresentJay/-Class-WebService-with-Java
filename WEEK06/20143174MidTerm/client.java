import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * client ② 클라이언트 클래스 이름은 Client 로 한다.
 */
public class client {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost/PersonMaker");

        ObjectInputStream in = new ObjectInputStream(url.openStream());
        ArrayList<Person> persons = (ArrayList) in.readObject();

        // ① 위 1의 ⑤번 항목에서 전송한 객체를 수신하고 역직렬화하여
        // 모든 Person객체에 대하여 이름, 나이, 성별, 전화번호, 출생년도를 출력하라.
        for (Person i : persons) {
            System.out.println(i.getData("name") + ", age:" + i.getAge() + ", gender:" + i.getGender() + ", "
                    + i.getData("tel") + ", year:" + i.getYear());
        }
    }
}