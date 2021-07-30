package springdatajpa.querydsl.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("테스트")
    void start_1() throws Exception {
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

    @Test
    @DisplayName("jpaRepository로 변경")
    void start_2() throws Exception {
        Member username1 = Member.builder().age(10).username("username1").build();
        Member username2 = Member.builder().age(11).username("username2").build();
        Member username3 = Member.builder().age(12).username("username3").build();

        memberRepository.save(username1);
        memberRepository.save(username2);
        memberRepository.save(username3);


        //given
        PageRequest sorting = PageRequest.of(0, 30, Sort.by(Sort.Direction.ASC, "age"));
        Page<Member> all = memberRepository.findAll(sorting);
        System.out.println("all.getContent().size() = " + all.getContent().size());
        //when

        //then
    }

    @Test
    @DisplayName("jpaRepository_querydsl")
    void start_3() throws Exception {
        //given
        Member username1 = Member.builder().age(10).username("username1").build();
        Member username2 = Member.builder().age(11).username("username2").build();
        Member username3 = Member.builder().age(12).username("username3").build();

        memberRepository.save(username1);
        memberRepository.save(username2);
        memberRepository.save(username3);
        //when

        MemberSearchCondition build = MemberSearchCondition.builder().build();

        List<MemberTeamDto> memberTeamDtos = memberRepository.searchCoditionMember(build);
        int size = memberTeamDtos.size();
        System.out.println("size = " + size);

        //then
    }

    @Test
    @DisplayName("jpaRepository_querydsl_paging")
    void start_4() throws Exception {
        //given
        Member username1 = Member.builder().age(10).username("username1").build();
        Member username2 = Member.builder().age(20).username("username2").build();
        Member username3 = Member.builder().age(30).username("username3").build();
        Member username4 = Member.builder().age(10).username("username4").build();
        Member username5 = Member.builder().age(40).username("username5").build();
        Member username6 = Member.builder().age(43).username("username6").build();
        Member username7 = Member.builder().age(50).username("username7").build();
        Member username8 = Member.builder().age(20).username("username8").build();
        Member username9 = Member.builder().age(11).username("username9").build();

        memberRepository.save(username1);
        memberRepository.save(username2);
        memberRepository.save(username3);
        memberRepository.save(username4);
        memberRepository.save(username5);
        memberRepository.save(username6);
        memberRepository.save(username7);
        memberRepository.save(username8);
        memberRepository.save(username9);
        //when

        MemberSearchCondition build = MemberSearchCondition.builder().ageLoe(30).build();
        PageRequest of = PageRequest.of(0, 3);
        Page<MemberTeamDto> memberTeamDtos = memberRepository.searchCoditionMemberPageCount(build, of);
        int size = memberTeamDtos.getContent().size();
        //then
        assertThat(size).isEqualTo(3);
        assertEquals(memberTeamDtos.getNumberOfElements(), 3);
        assertEquals(memberTeamDtos.getTotalElements(), 6);
    }

}
