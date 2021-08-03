package tutorial.study.servlet_nolecture.basic.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member("catsbi", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member foundMember = memberRepository.findById(savedMember.getId());
        assertThat(savedMember).isEqualTo(foundMember);
    }

    @Test
    void findById() {
        //given
        Member savedMember = memberRepository.save(new Member("catsbi", 20));

        //when
        Member foundMember = memberRepository.findById(savedMember.getId());

        //then
        assertThat(foundMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member savedMember1 = memberRepository.save(new Member("catsbi", 20));
        Member savedMember2 = memberRepository.save(new Member("crong", 30));

        //when
        List<Member> members = memberRepository.findAll();

        //then
        assertThat(members).hasSize(2);
        assertThat(members).contains(savedMember1, savedMember2);
    }

}