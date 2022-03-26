package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 이렇게 해둬도 @Controller처럼 동작한다. 클래스 레벨에서 컴포넌트 스캔의 대상 + RequestMapping
// @Component // 스프링 빈으로 들어감.
// @RequestMapping // RequestMappingHandlerMapping이 매핑 정보로 인식함.
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); // 뷰의 논리 이름
    }
}
