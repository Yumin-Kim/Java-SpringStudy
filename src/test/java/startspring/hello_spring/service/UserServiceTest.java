package startspring.hello_spring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import startspring.hello_spring.domain.Member;
import startspring.hello_spring.repository.MemberRepository;
import startspring.hello_spring.repository.MemmoryMemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private MemmoryMemberRepository memberRepository;
    private UserService  userService ;

    //테스트 케이스 작성시 독립적으로 실행되어야하기 때문에 Reposoitory를 한 케이스 실행시 재생성해준다.
    @BeforeEach
    public void createRepository(){
        memberRepository = new MemmoryMemberRepository();
        userService = new UserService(memberRepository);
    }
    @AfterEach
    public void clearRepository(){
        memberRepository.clearInstanceTest();
    }

    /**
     * 아래와 같은 방식으로 작성하면 편함!!
     * given
     * 어떤 데이터를 전송하여
     * when
     * 이것을 실행 했을때
     * than
     * 이런 결과를 보여준다.
     */
    @Test
    void join() {
        Member member = new Member();
        member.setName("hello");
        userService.join(member);

        Member member1 = userService.findOne(member.getId()).get();

        assertThat(member1).isEqualTo(member);
    }

    @Test
    public void valid_login(){
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


    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}