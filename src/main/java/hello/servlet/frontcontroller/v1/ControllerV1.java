package hello.servlet.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {

    // 서블릿의 service와 똑같이 구현한다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
