package startspring.hello_spring.singletonTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.AppConfig;
import startspring.hello_spring.member.MemberService;

public class SingleTonTest {

    @Test
    @DisplayName("싱글통 사용하지 않고 객체 클라이언트 조회시")
    void noSingleTonTest() {

        AppConfig appConfig = new AppConfig();

        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 사용하여 클라이언트 조회시")
    void applySingleTonTest() {
        SingleTonService instance1 = SingleTonService.getInstance();
        SingleTonService instance2 = SingleTonService.getInstance();
        Assertions.assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너를 활용한 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }
}
