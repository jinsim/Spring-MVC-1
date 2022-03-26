package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.Controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.Controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.Controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v1/ 하위의 어떤 url이 호출되어도, 해당 서블릿이 호출된다는 의미이다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
// FrontController는 서블릿이어야 한다.
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑 정보를 넣을 곳
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        // 기본 생성자가 호출될 때, 즉 서블릿이 처음 생성될 때 매핑 정보를 넣는다.
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 요청된 URL을 가져올 수 있다. Map에서 컨트롤러를 가져올 때 Key값으로 쓰인다.
        String requestURI = request.getRequestURI();

        // 조회하는 URL에 따라서 각각 다른 컨트롤러 객체가 반환되지만, 인터페이스로 꺼내오면 일관성있게 사용할 수 있다.
        // 다형성을 활용했다.
        ControllerV1 controller = controllerMap.get(requestURI);

        // 404인 경우엔 에러 상태 선택하고 return
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 조회가 된 경우에는 process를 호출해주면 된다.
        controller.process(request, response);
    }
}
