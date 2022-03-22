package hello.servlet.frontcontroller.v3;

import hello.servlet.frontcontroller.ModelView;
import hello.servlet.frontcontroller.MyView;
import hello.servlet.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);

        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        /*
        V2 코드
        MyView view = controller.process(request, response);
        view.render(request, response);
        */

        Map<String, String> paramMap = createParamMap(request);
        // 컨트롤러의 프로세스에 paramMap을 전해주고, 반환값을 ModelView로 받는다.
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();// Controller에서 정해준 View의 논리 이름이 반환된다.
        MyView view = viewResolver(viewName);// 물리 경로로 변환해서 MyView를 호출한다.

        // view.render(request, response);  // v2의 render
        view.render(mv.getModel(), request, response);

    }

    // 계층을 지키기 위해, 구체적인 코드는 메소드로 뽑는 것이 좋다.
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // request의 파라미터들을 전부 꺼내서 paramMap에 넣는다.
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
