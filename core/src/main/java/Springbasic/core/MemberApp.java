package Springbasic.core;

import Springbasic.core.member.Grade;
import Springbasic.core.member.Member;
import Springbasic.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(memberRepository);


        // 직접 memberService를 생성하는것이 아닌, appconfig에서 불러온다.
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // spring 전환
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // appconfig에 있는 환경설정 정보를 가지고 스프링이 컨테이너에 알아서 객체 등록해준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        Member member2 = new Member(2L, "memberB", Grade.VIP);
        memberService.join(member);
        memberService.join(member2);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());

        System.out.println("new member2 = " + member2.getName());
        Member findMember2 = memberService.findByMemberName("memberB");
        System.out.println("findMember2 = " + findMember2.getName());
    }
}
