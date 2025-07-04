package Springbasic.core.discount;

import Springbasic.core.member.Grade;
import Springbasic.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPolicy implements  DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // enum ==
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
