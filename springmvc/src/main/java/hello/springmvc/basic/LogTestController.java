package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j // LOMBOK이 제공하는 애너테이션.
@RestController // @RequestMapping이 붙은 메소드에서, String 자체가 그냥 반환된다. (@Controller는 뷰 이름이 반환돼서 뷰리졸버를 거쳐야함.)
public class LogTestController {

    // getClass()는 LogTestController.class와 동일하다.
//    private final Logger log = LoggerFactory.getLogger(getClass()); // @Slf4j가 자동으로 해준다.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

/*
        System.out.println("name = " + name); // 과거 방식
        log.info(" info log={} ", name); // 새로운 방식 name이 {} 안에 치환된다.
*/

        // 로그를 찍을 때 레벨을 정할 수 있다. (밑에 있는 것이 더 심각한 레벨이다.)
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
