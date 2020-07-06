import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * postservlet
 */

@WebServlet("/postservlet")
public class postservlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String age = req.getParameter("age");

        PrintWriter out = resp.getWriter();
        out.println("hello " + name + ":" + age);
    }

}