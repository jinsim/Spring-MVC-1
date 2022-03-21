package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    // Servlet이 Controller 역할을 한다.
    // MVC 패턴에서는 항상 Controller를 통해 View로 들어가야 한다.

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MvcMemberFormServlet.service");
        // 보여줄 View 경로
        String viewPath = "/WEB-INF/views/new-form.jsp";
        // Controller에서 View를 연결
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        // 서블릿에서 jsp를 호출
        dispatcher.forward(request, response);
    }
}
