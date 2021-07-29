package springdatajpa.querydsl.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springdatajpa.querydsl.domain.Member;

import java.util.Arrays;
import java.util.List;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("테스트")
    void start_1() throws Exception{
        //given
        Member username1 = Member.builder().age(10).username("username1").build();
        Member username2 = Member.builder().age(11).username("username2").build();
        Member username3 = Member.builder().age(12).username("username3").build();

        memberRepository.save(username1);
        memberRepository.save(username2);
        memberRepository.save(username3);

        List<Member> byUsernames = memberRepository.findByUsernames(Arrays.asList("username1", "username2"));

        //when

        //then
    }
}
