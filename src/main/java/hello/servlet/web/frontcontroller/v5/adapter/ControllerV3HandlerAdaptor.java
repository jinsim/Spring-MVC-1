package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdaptor implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        // ControllerV3 인터페이스를 구현한 무언가가 넘어오면, true를 반환한다.
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // supports에서 ControllerV3만 지원한다고 설정되어 있으므로, ControllerV3로 캐스팅 해도 된다.
        ControllerV3 controller = (ControllerV3) handler;
        // ControllerV3의 process를 호출하기 위해서는 paramMap이 필요함.
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        // 반환타입이 ModleView이다.
        return mv;

    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // request의 파라미터들을 전부 꺼내서 paramMap에 넣는다.
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
