package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        /* V2에서 파라미터 가져오는 법
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        */

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        // ModelView의 viewName으로 "save-result"를 넎는다.
        ModelView mv = new ModelView("save-result");
        // ModelView의 model에 member를 넣는다.
        mv.getModel().put("member", member);
        return mv;
    }
}
