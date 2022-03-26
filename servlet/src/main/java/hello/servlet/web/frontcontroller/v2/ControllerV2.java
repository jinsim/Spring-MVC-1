package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // 기존에는 void였고, Controller가 알아서 JSP forward로 렌더링했었다.
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
