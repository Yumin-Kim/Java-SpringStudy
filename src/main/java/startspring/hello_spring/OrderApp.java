package startspring.hello_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.member.Grade;
import startspring.hello_spring.member.Member;
import startspring.hello_spring.member.MemberService;
import startspring.hello_spring.member.MemberServiceImpl;
import startspring.hello_spring.order.Order;
import startspring.hello_spring.order.OrderService;
import startspring.hello_spring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        Member member = new Member(1L, "member1", Grade.VIP);
        memberService.join(member);

        Order itme1 = orderService.createOrder(1L, "Item1", 50000);

        System.out.println("order = "+ itme1);
        System.out.println("order.calculatePrice = "+ itme1.calculatePrice());

    }
}
