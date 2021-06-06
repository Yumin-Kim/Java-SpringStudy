package startspring.hello_spring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

     MemberService memberService;

    @Test
    void join(){
        Member member = new Member(1L, "Spring1", Grade.BASIC);
        memberService.join(member);
        Member findJoinMember = memberService.findMember(member.getId());
        Assertions.assertThat(member).isEqualTo(findJoinMember);
    }
}

