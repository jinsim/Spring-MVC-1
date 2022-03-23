package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // 기존 V4 코드
    // private Map<String, ControllerV3> controllerMap = new HashMap<>();
    // final은 큰 의미 없음. 기존에는 컨트롤러 타입을 확실하게 정했지만, 이번에는 모든 핸들러가 다 가능해야 하므로 Object로 설정했다.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    // 어댑터가 여러 개 담겨있고, 그 중 선택해서 사용해야하기 때문에
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        // Object는 모든 자바 객체의 최상위이므로, 어떤 자바 객체든지 담길 수 있다.
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v4도 적용 가능하도록 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        // 핸들러 어댑터도 List에 넣는다.
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        // v4도 적용 가능하도록 추가
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // 어댑터에 handle을 호출해서 ModelView를 가져온다.
        ModelView mv = adapter.handle(request, response, handler);

        // 가져온 ModelView에서 ViewName을 가져오고, viewResolver에 넣어 MyView를 가져온다.
        MyView view = viewResolver(mv.getViewName());
        // view의 render를 호출한다.
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        // request 파라미터에서 URL을 가져와서, handlerMappingMap에서 핸들러를 가져온다.
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        // handlerAdapters에서 반복문을 돌아서 해당 핸들러를 지원하는 어댑터를 찾아서 반환한다.
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        // 업다면 에러를 던진다.
        throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
