package startspring.hello_spring.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import startspring.hello_spring.AppConfig;
import startspring.hello_spring.discount.RateDiscountPolicy;
import startspring.hello_spring.member.*;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void assignObject(){
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
        this.orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member1 = new Member(memberId, "Spring1", Grade.VIP);
        memberService.join(member1);
        Member findMemberId = memberService.findMember(memberId);
        Order itme1 = orderService.createOrder(findMemberId.getId(), "itme1", 10000);

        Assertions.assertThat(itme1.getDiscountPrice()).isEqualTo(1000);

    }


}
