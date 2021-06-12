package startspring.hello_spring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
public class MemberServiceTest {

    private  MemberService memberService;
    @Test
    void join() {
        memberService = new MemberServiceImpl(new MemoryMemberRepository());
        Member member = new Member(1L, "Spring1", Grade.BASIC);
        memberService.join(member);
        Member findJoinMember = memberService.findMember(member.getId());
        Assertions.assertThat(member).isEqualTo(findJoinMember);
    }
}

