package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @Controller를 하지 않아도 된다. 대신, 핸들러 매핑을 따로 처리해야 한다.
@Component("/springmvc/old-controller")     // 스프링 빈의 이름을 "/springmvc/old-controller" 로 한다.
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form"); // 뷰의 논리 이름을 넣은 ModelAndView를 반환
    }
}
