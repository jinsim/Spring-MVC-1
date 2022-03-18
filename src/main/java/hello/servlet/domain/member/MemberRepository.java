package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    // static으로 선언해두었으므로, new MemberRepository가 아무리 많아도 아래 것들은 하나만 생성되고 공유된다.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    // 아래에서 싱글톤으로 만들었으므로, static을 제외해도 된다. 어차피 하나인 게 보장이 되기 때문이다!

    // 싱글톤으로 만들 것이다. 톰캣 띄울 때만 스프링을 사용하고, 그 외에는 스프링을 쓰지 않을 것이기 때문이다.
    private static final MemberRepository instance = new MemberRepository();

    // 무조건 얘로만 조회한다.
    public static MemberRepository getInstance() {
        return instance;
    }

    // 싱글톤을 만들 때는, 아무나 생성하지 못하도록 private으로 생성자를 막아야 한다.
    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence); // member 객체의 id를 sequence에 하나 더한 것으로 설정하고
        store.put(member.getId(), member); // store에 id와 member를 넣는다.
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // store에 있는 모든 값들을 꺼내서, 새로운 ArrayList에 담아준다.
        // new ArrayList에 값을 넣거나 조작해도, store에 있는 value 리스트를 건들고 싶지 않아서
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // 테스트에서만 사용함. 스토어 다 날리기.
    }
}
