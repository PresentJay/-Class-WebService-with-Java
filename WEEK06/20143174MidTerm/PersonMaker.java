import java.io.*;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

// ⑥ 서블릿을 구현한 클래스 이름은 PersonMaker로 하고 서블릿의 이름 또한 PersonMaker로 한다.
// url의 이름은 PersonMaker로 한다.
@WebServlet("/PersonMaker")
public class PersonMaker extends HttpServlet {
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("utf-8");

        // ⑤ person.txt 파일에 있는 모든 사람에 대한 객체를 ArrayList에 담고
        // 이것을 서블릿을 호출한 클라이언트에게 직렬화하여 전송한다.
        // ⑦ person.txt 파일은 반드시 톰캣홈디렉토리/bin 에 존재하여야
        // PersonMaker에서 별도의 경로를 명시하지 않아도 person.txt 파일에 접근할 수 있다.
        ArrayList<Person> persons = new ArrayList<Person>();
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("person.txt")));
        String str = "";

        // 1. person.txt 파일에 존재하는 데이터의 개수(사람의 수)에 상관없이 위 내용이 항상 실행되도록 하라.
        while ((str = in.readLine()) != null) {
            persons.add(new Person(str));
        }

        ObjectOutputStream out = new ObjectOutputStream(response.getOutputStream());
        out.writeObject(persons);
        out.flush();
        in.close();
    }
}