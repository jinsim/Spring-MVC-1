package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    // 회원 리포지토리에 저장하면 id가 자동으로 발급될 것임.
    private Long id;
    private String username;
    private int age;

    // 기본 생성자
    public Member(String hello, int i) {
    }

    // 생성자
    public Member(Long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
