package Springbasic.core.member;

public class MemberServiceImpl implements  MemberService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자를 만들어준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member findByMemberName(String memberName) {
        return memberRepository.findByMemberName(memberName);
    }

    //test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
