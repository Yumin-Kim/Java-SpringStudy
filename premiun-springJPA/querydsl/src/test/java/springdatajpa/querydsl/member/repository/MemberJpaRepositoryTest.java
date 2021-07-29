package springdatajpa.querydsl.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import springdatajpa.querydsl.domain.Member;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@DataJpaTest
@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    @DisplayName("테스트 코드")
    void start_1() throws Exception{
        //given
        Member username1 = Member.builder().age(10).username("username1").build();
        Member username2 = Member.builder().age(11).username("username2").build();
        Member username3 = Member.builder().age(12).username("username3").build();
        memberJpaRepository.save(username1);
        memberJpaRepository.save(username2);
        memberJpaRepository.save(username3);
        //when
        Member findMember = memberJpaRepository.findById(username1.getId()).orElseThrow(() -> new IllegalStateException("존재하지 않는 회원 입니다."));
        List<Member> findUsernames = memberJpaRepository.findByUserName("username1");
        List<String> strings = Arrays.asList("username1", "username2");
        List<Member> byUserName = memberJpaRepository.findByUsernames(strings);
        List<Member> all = memberJpaRepository.findAll();
        List<Member> all_querydsl = memberJpaRepository.findAll_querydsl();
        //then
        assertEquals(findMember.getUsername(),"username1");
        assertEquals(findUsernames.size(),1);
        assertEquals(findUsernames.get(0).getUsername(),"username1");
        assertThat(all).containsExactly(username1,username2,username3);
        assertThat(all_querydsl).containsExactly(username1,username2,username3);
        assertEquals(byUserName.size(),2);
    }

}