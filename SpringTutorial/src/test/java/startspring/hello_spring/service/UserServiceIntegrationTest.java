package startspring.hello_spring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import startspring.hello_spring.ConfigSpring;
import startspring.hello_spring.domain.Member;
import startspring.hello_spring.repository.MemmoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//순수 자바 코드를 테스트 하는것을 단위 테스트(해당 테스트는 시간소모가 적음)
// DB의 트랜잭션을 하는 자바 테스트 코드는 통합(Integration) 테스트라고 함 >> 하지만 통합 테스트 보다는 단위 테스트를 선호함
@SpringBootTest //해당 어노테이션을 사용하면 tomcat이 자동을 실행된다.
@Transactional // 자동으로 트랜잭션을 일어난다음 롤백을 해주는 어노테이션 >> 테스트간에 DB관련 부가적인 side Effect의 부산물을 제거할 필요가 없다.
class UserServiceIntegrationTest {

    UserService userService =new UserService(new MemmoryMemberRepository());;



    @Test
    void join() {
        Member member = new Member();
        member.setName("hello");
        System.out.println("userService = " + userService);
        Long sasveId = userService.join(member);

        Member member1 = userService.findOne(sasveId).get();


        assertThat(member1.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void valid_login() {
        Member member1 = new Member();
        member1.setName("Hello");

        Member member2 = new Member();
        member2.setName("Hello");
        userService.join(member1);

        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class, () -> userService.join(member2));
        assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 이름입니다");
//        try {
//            userService.join(member2);
//            fail("");
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다");
//        }

    }

}