package startspring.hello_spring.singletonTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.AppConfig;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemberServiceImpl;
import startspring.hello_spring.order.OrderServiceImpl;
import startspring.hello_spring.repository.MemmoryMemberRepository;

public class ConfigurationSingleTon {
    @Test
    @DisplayName("Beab에 등록된 생성자 함수인자의 객체가 싱글톤으로 구현되어 있는지 확인")
    void configurationTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orederService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository2 = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository = memberService.getMemberRepository();
        MemberRepository memberRepository1 = orederService.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
//        Assertions.assertThat(memberRepository1).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        //class startspring.hello_spring.AppConfig$$EnhancerBySpringCGLIB$$97257a6b
        //getClass()했을때 패키지명.class명 에서 종료되어야 하는데
        //Enhancer...SpringCGLIB$$asdasd12(클래스정보)
        //개발자가 만든 클래스가 아니라 spring bean을 등록하는 과정에서 개발자가 모르는 bean을 등록한다.
        //CGLIB라는 바이트 코드 조작 라이브러리를 사용해서 AppConfig 클래스 상속받은 임의의 다른 클래스를 만들고 그 다른 클래스를 스프링 빈으로 등록한것이다.CGLIB을 자식 클래스로 가지게된다.
        //@Configuration 제거하고 재실행하게 되면 Bean으로 등록되지만 싱글톤으로 객체를 생성하지 않게된다.
        // 처음 테스트한 코드를 보게되면 MemberRepository를 성성하는 코드가 2번있어서 @Configuration을 사용하면 CGLIB을 통해서 싱글 톤으로 구성되어 중복되어 생성하지 않지만
        // 제거시 2번 생성되는 모습을 보게된다. 순수 자바 코드를 실행하게된다.
        //Autowired를 통해 의존 관계를 주입하는 방법도 있다.
        //@Bean사용시 등록은 되지만 싱글톤은 보장해주지 않는다.-


    }

}
