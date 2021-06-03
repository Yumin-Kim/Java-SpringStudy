package startspring.hello_spring.repository;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import startspring.hello_spring.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//https://pjh3749.tistory.com/240 test코드 실행 순서 >> 하지만 순서에 의존적으로 테스트 코드를 작성하면 안된다.

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemmoryMemberRepositoryTest {


    MemmoryMemberRepository memberRepository = new MemmoryMemberRepository();

    @AfterEach
    public void clear(){
        memberRepository.clearInstanceTest();
    }

    @Test //@Test어너테이션을 통해 자동으로 해당 메소드가 실행되도록 해줌
    @Order(1)
    public void save(){
        Member member = new Member();
        member.setName("Hello");
        memberRepository.save(member);

        Member result = memberRepository.findByName(member.getName()).get();
        System.out.println(member+"###");
        System.out.println(result);
        //Juni사용하여 진행한 코드
        //Assertions.assertEquals(result,member);
        //assertj는 Assertion을 편하게 사용하기 위해서 사용하며 import static 해온다
        //https://offbyone.tistory.com/283
        // import static은 라이브러리에서 static으로 되어 있는 메소드를 인스턴스화 할 필요 없이 바로 사용할 수 있다.
        assertThat(result).isEqualTo(member);
    }



    @Test
    @Order(3)
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);
        List<Member> result = memberRepository.findAll();
        System.out.println("________________________________");
        result.stream().forEach(e -> System.out.println(e));

        assertThat(result.size()).isEqualTo(2);

    }

    @Test
    @Order(2)
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        memberRepository.save(member2);

        System.out.println(member1);
        System.out.println(member2);

        Member result = memberRepository.findByName("Spring1").get();

        System.out.println(result);

        assertThat(result).isEqualTo(member1);

    }

}


