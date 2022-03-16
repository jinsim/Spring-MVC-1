package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// name : 서블릿 이름, urlPatterns : URL 매핑
@WebServlet(name = "helloServlet", urlPatterns = "/hello") // 보통 이름은 클래스 이름의 카멜케이스로 적는다.
public class HelloServlet extends HttpServlet {

    @Override
    // HTTP 요청이 오면, WAS, 서블릿 컨테이너가 request reponse 객체를 만들어서 서블릿에 준다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // /hello?username=kim 이렇게 요청이 오면, username에는 kim이 들어간다.
        String username = request.getParameter("username"); // 쿼리 파라미터에서 username에 맞는 값을 username에 저장한다.
        System.out.println("username = " + username);

        // 응답 메시지 보내기
        response.setContentType("text/plain");  // 단순 문자로 보낼 것이다.
        response.setCharacterEncoding("utf-8");  // 문자에 대한 인코딩 정보. EUC-KR은 이제 쓰면 안된다. 2020년이 넘었으므로
        // 위에 2개는 Header에 들어간다.
        response.getWriter().write("hello " + username);  // Body에 데이터가 들어간다.
    }
}
