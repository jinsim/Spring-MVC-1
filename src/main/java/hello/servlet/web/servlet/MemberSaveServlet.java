package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("MemberSaveServlet.service");
//        // 쿼리 파라미터든, Form 데이터든, username=kim&age=20 과 같은 형식으로 되어있으면 getParameter로 읽을 수 있다.
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age")); // String으로 오므로 int로 변경
//
//        System.out.println("age = " + age);
//        System.out.println("username = " + username);
//        Member member = new Member(username, age);
//        memberRepository.save(member);
//
//        // 저장이 잘 되었는지 보기 위해, 응답을 HTML 코드로 내린다.
//        response.setContentType("text/html");
//        response.setCharacterEncoding("utf-8");
//
//        System.out.println("member = " + member);
//        System.out.println("memb = " + member.getUsername());
//        System.out.println("member.getUsername() = " + member.getUsername());

        System.out.println("MemberSaveServlet.service");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        System.out.println("member = " + member); memberRepository.save(member);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" + "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" + "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" + "</body>\n" +
                "</html>");
    }
}
