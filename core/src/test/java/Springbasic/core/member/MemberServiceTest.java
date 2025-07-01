package Springbasic.core.member;

import Springbasic.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//    MemberService memberService = new MemberServiceImpl(memberRepository);
    MemberService memberService;

    // 테스트 실행하기전에 돌아간다.
    @BeforeEach
    public void beforEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        Member findMemberByName = memberService.findByMemberName("memberA");

        // then
        Assertions.assertThat(member).isEqualTo(findMember);
        Assertions.assertThat(member).isEqualTo(findMemberByName);
    }
}
