package Springbasic.core.order;

import Springbasic.core.annotation.MainDiscountPolicy;
import Springbasic.core.discount.DiscountPolicy;
import Springbasic.core.member.Member;
import Springbasic.core.member.MemberRepository;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final가지고 생성자 만들어준다.
public class OrderServiceImpl implements  OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 추상화에만 의존하게 변경 => 인터페이스에만 의존
    // -> 이것만 변경하면 null 이슈 발생
    // -> 누군가 클라이언트 OrderServiceImpl 에 discountPolicy 의 구현객체를 대신 생성해줘야 한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // dip 준수하는 중임 인터페이스에만 의존

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
