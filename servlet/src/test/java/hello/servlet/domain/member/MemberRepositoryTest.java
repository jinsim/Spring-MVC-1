package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    // 테스트할 대상 가져오기. 싱글톤이므로 new 생성자 대신 getInstance를 활용한다.
    MemberRepository memberRepository = MemberRepository.getInstance();

    // 테스트가 끝나면, 저장소를 초기화하기 위함
    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        // given 이런 게 주어졌을 때
        Member member = new Member("hello", 20);

        // when 이런 걸 실행했을 때
        Member savedMember = memberRepository.save(member);

        // then 결과가 이거여야 한다
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2); // result의 개수가 2개인가.
        assertThat(result).contains(member1, member2); // result 안에 member1, member2가 있는가.
    }
}