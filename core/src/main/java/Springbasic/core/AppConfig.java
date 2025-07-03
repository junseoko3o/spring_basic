package Springbasic.core;

import Springbasic.core.discount.DiscountPolicy;
import Springbasic.core.discount.RateDiscountPolicy;
import Springbasic.core.member.MemberService;
import Springbasic.core.member.MemberServiceImpl;
import Springbasic.core.member.MemoryMemberRepository;
import Springbasic.core.order.OrderService;
import Springbasic.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어플리케이션에 대한 환경 구성 모음
// 구현 객체를 생성하고 연결하는 역할
@Configuration // spring 전환 , 설정정보
public class AppConfig {

    // appconfig 를 통해서 memberService를 불러 쓴다
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    // 역할이 드러나도록 리팩토링
    @Bean // 각 메서드에 Bean , 이러면 스프링 컨테이너에 등록이 된다.
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService()");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository()");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService()");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        // fix -> rate로 변경하였지만, AppConfig인 구성영역만 변경했지 그외 사용영역들은 전혀 변경하지 않고도 애플리케이션의 기능을 확장 가능
        return new RateDiscountPolicy();
    }
}
