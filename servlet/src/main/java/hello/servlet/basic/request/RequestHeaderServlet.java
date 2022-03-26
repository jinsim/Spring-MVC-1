package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
        response.getWriter().write("ok");
    }

    //start line 정보
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocal() = " + request.getProtocol()); // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // /request-test
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // username=hi
        System.out.println("request.getQueryString() = " + request.getQueryString()); // 쿼리 파라미터
        System.out.println("request.isSecure() = " + request.isSecure()); //http 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    // 사실 이렇게 안찍어도 됨. 로그 설정 해주면 보임.
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");
/*
옛날 방식. Enumeration으로 반환되므로 while 돌려서 다음 요소가 있으면 꺼낸다.
request.getHeaderNames()를 호출하면 HTTP 요청 메시지에 있는 모든 헤더 정보를 다 꺼내서 출력할 수 있다.
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }
*/
        // 요즘 방식. request.getHeaderNames()는 그대로 쓰는데, Iterator로 변경해서 사용한다.
        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName ->
                        System.out.println(headerName + ":" + request.getHeader(headerName)));
//        request.getHeader("host") // 원하는 헤더의 값만 꺼낼 수도 있음.
        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]"); // 웹 브라우저 선호 언어
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale()); // 가장 선호되는 언어
        System.out.println();

        System.out.println("[cookie 편의 조회]"); // 로그인에서 주로 사용됨. 쿠키도 HTTP 헤더에 담긴다.
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의 조회]"); // GET은 Content를 거의 안보인다. HTTP 메시지 바디에 뭐가 있어야 의미를 가진다.
        System.out.println("request.getContentType() = " + request.getContentType());
        System.out.println("request.getContentLength() = " + request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());

        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    // 기타 정보
    // HTTP 메시지에 오는 정보들은 아니고, 내부에서 네트워크 커넥션이 맺어진 정보들로 알 수 있다.
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");

        System.out.println("[Remote 정보]"); // 요청이 온 것에 대한 정보
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]"); // 나의 서버에 대한 정보
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());

        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }


}
