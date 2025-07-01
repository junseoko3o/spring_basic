package Springbasic.core.member;

// 인터페이스
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);

    Member findByMemberName(String memberName);
}
