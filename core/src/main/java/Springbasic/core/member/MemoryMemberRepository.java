package Springbasic.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements  MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    // static 이라 시작되고 나서 모든 사용자들이 이 Map을 사용
    // HashMap은 여러개 스레드가 동시에 접근하면 문제가 생김

    // 제대로 된 방법
    // private static final Map<Long, Member> store = new ConcurrentHashMap<>();
    // HashMap에 락을 걸어버림. 근데 이건 전체 Map에 락이 걸려 성능 저하 이슈.
    // private static final Map<Long, Member> store = Collections.synchronizedMap(new HashMap<>());

    // ConcurrentHashMap
    // 여러 쓰레드가 동시에 접근해도 내부에서 잘 나눠준다.
    // 자동으로 락을 걸고 풀어줘 신경 쓸게 없어짐.

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

    @Override
    public Member findByMemberName(String memberName) {
        return store.values().stream()
                .filter(member -> member.getName().equals(memberName))
                .findFirst()
                .orElse(null);
    }
}
