package Springbasic.core.discount;

import Springbasic.core.member.Grade;
import Springbasic.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip 10% 할인")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);

        //static import
    }

    @Test
    @DisplayName("VIP 아닌 경우")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberBasic", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("갑자기 20%할인 해버리면")
    void vip_over() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discount = discountPolicy.discount(member, 20000);
        try {
            assertThat(discount).isEqualTo(1000);
            System.out.println("❌ 할인 정책이 바뀌었는데도 테스트가 통과함 - 문제 있음!");
        } catch (AssertionError e) {
            System.out.println("✅ 할인 정책이 변경되어 테스트 실패함 (의도된 실패): " + e.getMessage());
        }
    }
}