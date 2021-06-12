package startspring.hello_spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import startspring.hello_spring.discount.DiscountPolicy;
import startspring.hello_spring.discount.RateDiscountPolicy;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemberService;
import startspring.hello_spring.member.MemberServiceImpl;
import startspring.hello_spring.member.MemoryMemberRepository;
import startspring.hello_spring.order.OrderService;
import startspring.hello_spring.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
