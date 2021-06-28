package startspring.hello_spring.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import startspring.hello_spring.AppConfig;
import startspring.hello_spring.discount.DiscountPolicy;
import startspring.hello_spring.discount.FixDisocuntPolicy;
import startspring.hello_spring.discount.RateDiscountPolicy;
import startspring.hello_spring.member.MemberRepository;
import startspring.hello_spring.member.MemberService;
import startspring.hello_spring.member.MemberServiceImpl;
import startspring.hello_spring.member.MemoryMemberRepository;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    AnnotationConfigApplicationContext sameContext = new AnnotationConfigApplicationContext(SameConfig.class);
    AnnotationConfigApplicationContext sameInterfaceToContext = new AnnotationConfigApplicationContext(InheriteConfig.class);

    @Configuration
    static class SameConfig {
        @Bean
        MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    @Configuration
    static class InheriteConfig {
        @Bean
        DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        DiscountPolicy fixedDiscountPolicy() {
            return new FixDisocuntPolicy();
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회")
    void findByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBenaByType() {
        MemberService bean = ac.getBean(MemberService.class);
        assertThat(bean).isInstanceOf(MemberService.class);
        //인터페이스가 아닌 실제 구현 클래스 조회
        // 구현체로 구현할시 유연성이 떨어짐으로 하지 최대한 사용하지 않는것을 추천한다.
        MemberServiceImpl bean1 = ac.getBean(MemberServiceImpl.class);
        assertThat(bean1).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회시 존재하지 않은 빈일때")
    void notFindByType() {
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("asdasdasd", MemberService.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 두개 이상 존재하며 , 중복 오류발생")
    void findBeanNameDuplicate() {
        //NoUniqueBeanDefinitionException 에러가 발생하게 된다.
        assertThrows(NoUniqueBeanDefinitionException.class, () -> sameContext.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시  같은 타입이 둘 이상이면, 빈이름을 지정해야한다.")
    void findBeanByName() {
        MemberRepository memberRepository1 = sameContext.getBean("memberRepository1", MemberRepository.class);

        System.out.println(MemberRepository.class);
        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = sameContext.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 조회시 , 자식이 둘이상 존재시 중복 오류 발생")
    void findParentInheriteClass() {
        //NoUniqueBeanDefinitionException이와같은 에러 발생
//        DiscountPolicy bean = sameInterfaceToContext.getBean(DiscountPolicy.class);
        System.out.println(DiscountPolicy.class+"        "+RateDiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,()-> sameInterfaceToContext.getBean(DiscountPolicy.class));
    }
}
