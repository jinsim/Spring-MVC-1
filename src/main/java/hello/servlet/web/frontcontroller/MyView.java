package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

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

    // v3부터 사용한다. 기존 render와 로직이 똑같은데, model이 추가되었다.
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 의미를 정확히 전달해주기 위해 메소드로 뺐다. Model에 있는 데이터를 RequestAttribute에 옮긴다.
        modelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);

    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        // forEach로 model에 있는 모든 값을 꺼내서 request에 값을 다 담는다.
        model.forEach((key, value) -> request.setAttribute(key, value));
    }
}
