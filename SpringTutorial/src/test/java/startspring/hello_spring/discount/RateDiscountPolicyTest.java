package startspring.hello_spring.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import startspring.hello_spring.member.Grade;
import startspring.hello_spring.member.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 되어야한다.")
    void vip_discount(){
        Member memberVIP = new Member(1L, "MemberVIP", Grade.VIP);
        int discount = discountPolicy.discount(memberVIP, 50000);
        assertThat(discount).isEqualTo(5000);
    }

    @Test
    @DisplayName("VIP는 아닌경우")
    void not_vip_discount(){
        Member memberBasic = new Member(1L, "MemberBASIC", Grade.BASIC);
        int discount = discountPolicy.discount(memberBasic, 50000);
        assertThat(discount).isEqualTo(0);
    }


}