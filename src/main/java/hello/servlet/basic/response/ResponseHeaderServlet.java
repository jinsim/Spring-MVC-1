package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [ststus-line]
        response.setStatus(HttpServletResponse.SC_OK); // 응답 코드 지정 가능

//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 응답 코드 수정

        // [response-headers]
        // 인코딩 타입을 지정하지 않으면 톰캣이 임의로 넣음. 한글 깨질 수도 있음
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 캐시를 완전히 무효화 하겠다.
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache"); // 과거 버전까지 캐시를 없앤다.
        response.setHeader("my-header", "hello"); // 임의로 내가 정한 헤더

        //[Header 편의 메서드]
//        content(response);
//        cookie(response);
//        redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    // Content 편의 메소드
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2

        //response.setHeader("Content-Type", "text/plain;charset=utf-8"); // 굳이 setHeader를 쓰지 않아도 된다.

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        //response.setContentLength(2); //(생략시 자동 생성)
    }

    // 쿠키 편의 메소드
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    // redirect 편의 메소드
    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html

        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html");

        response.sendRedirect("/basic/hello-form.html");
    }


}
