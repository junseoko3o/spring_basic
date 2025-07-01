package Springbasic.core.member;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

    Member findByMemberName(String memberName);
}
