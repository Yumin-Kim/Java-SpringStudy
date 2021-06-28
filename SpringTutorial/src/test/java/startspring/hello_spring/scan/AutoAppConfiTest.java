package startspring.hello_spring.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.AutoAppConfig;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemberService;
import startspring.hello_spring.member.MemberServiceImpl;
import startspring.hello_spring.order.OrderService;
import startspring.hello_spring.order.OrderServiceImpl;

public class AutoAppConfiTest {
    @Test
    void basicScan(){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService bean = annotationConfigApplicationContext.getBean(MemberService.class);
        Assertions.assertThat(bean).isInstanceOf(MemberService.class);

        OrderServiceImpl bean1 = annotationConfigApplicationContext.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean1.getMemberRepository();
//
        System.out.println("memberRepository = " + memberRepository);

    }
}
