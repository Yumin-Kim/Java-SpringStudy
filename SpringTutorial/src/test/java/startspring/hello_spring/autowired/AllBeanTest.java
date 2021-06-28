package startspring.hello_spring.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.AutoAppConfig;
import startspring.hello_spring.discount.DiscountPolicy;
import startspring.hello_spring.member.Grade;
import startspring.hello_spring.member.Member;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDisocuntPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);

        discountPrice = discountService.discount(member, 20000, "fixDisocuntPolicy");
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);


    }

    static class  DiscountService{

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap,List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int i, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountPolicy = " + discountCode);
            return discountPolicy.discount(member, i);
        }
    }

}
