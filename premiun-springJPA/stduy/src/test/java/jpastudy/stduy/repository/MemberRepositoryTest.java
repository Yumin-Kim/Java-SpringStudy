package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("JpaRepository상속 활용하여 테스트")
    void start_1() throws Exception{
        //given
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());
        Member member = new Member("username");
        Team teamName1 = new Team("teamName1");
        Team teamName2 = new Team("teamName1");
        //when
        Member saveMember = memberRepository.save(member);
        //then
        assertNotNull(saveMember.getUsername());
        assertNotNull(memberRepository);
    }

    @Test
    @DisplayName("JpaRepository의 페이징 메소드 활용")
    void start_2() throws Exception{

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"username"));
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC,"username"));
        Page<Member> pageMembers = memberRepository.findByAge(10, pageRequest);
        //then
        List<Member> resultPagingMembers = pageMembers.getContent();
        pageMembers.getContent().stream()
                .forEach(member->{
                    System.out.println("member.toString() = " + member.toString());
                });
        System.out.println("pageMembers.getTotalElements() = " + pageMembers.getTotalElements());
        //        assertAll(
//                ()->assertEquals(resultPagingMembers.size(),3),
//                ()->assertTrue(pageMembers.getTotalPages() == 1)
//        );
    }
}
