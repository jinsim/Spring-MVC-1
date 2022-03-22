package hello.servlet.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 서블릿의 service와 구조를 똑같이 한다.
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 기존에 Controller에서 View를 가져오기 위해서 했던 로직들을 적는다.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
        // forward까지 하면 JSP가 렌더링 된다.
    }
}
