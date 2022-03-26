package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // 기존 ControllerV1, V2와는 달리, 서블릿 기술들을 가져오지 않았다.
    // 우리 프레임워크에 종속적인 것이지, 서블릿에 종속적이지 않다.
    ModelView process(Map<String, String> paramMap);
}
