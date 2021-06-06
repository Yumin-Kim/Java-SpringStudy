package startspring.hello_spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import startspring.hello_spring.member.Grade;
import startspring.hello_spring.member.Member;
import startspring.hello_spring.member.MemberService;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member1 = new Member(1L, "Spring1", Grade.BASIC);
        memberService.join(member1);

        Member result = memberService.findMember(member1.getId());
        System.out.println("result Member"+result.getName());
        System.out.println("join Member"+member1.getName());
    }
}
