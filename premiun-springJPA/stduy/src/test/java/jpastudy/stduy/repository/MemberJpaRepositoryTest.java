package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import jpastudy.stduy.domain.dto.MemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("테스트")
    void start_1() throws Exception {
        //given
        Member member = new Member("username");
        Team teamName1 = new Team("teamName1");
        Team teamName2 = new Team("teamName1");
        Member saveMember = memberJpaRepository.save(member);
        Member member1 = memberJpaRepository.find(saveMember.getId());

        //then
        assertEquals(member1.getUsername(), member.getUsername());
    }

    @Test
    @DisplayName("테스트 JpaRepository사용")
    void start_2() throws Exception {
        //given
        Member member = new Member("username");
        Member saveMember = memberRepository.save(member);
        member.setAge(10);
        //when
        Optional<Member> findByMember = memberRepository.findById(saveMember.getId());
        //then
        findByMember.ifPresent(member1 -> {
            assertEquals(saveMember.getUsername(), member1.getUsername());
        });
    }

    @Test
    @DisplayName("엔티티 변경 감지")
    void start_3() throws Exception {
        //given
        Member member1 = new Member("Member1");
        Member member2 = new Member("Member1");

        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //when
        Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
        Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
        findMember1.setAge(10);

        long count = memberJpaRepository.count();
        List<Member> all = memberJpaRepository.findAll();
        //then
        assertEquals(findMember1.getUsername(), member1.getUsername());
        assertEquals(findMember2.getUsername(), member2.getUsername());
        System.out.println("all.size() = " + all.size());
        assertAll(
                () -> assertNotNull(count),
                () -> assertNotNull(all),
                () -> assertTrue(count == all.size())

        );
    }

    @Test
    @DisplayName("JPQL로 작성한 findByUserAndAgeGreaterThen.")
    void start_4() throws Exception {
        //given
        List<Member> member = memberJpaRepository.findByUserAndAgeGreaterThen("Member1", 10);
        List<Member> member1 = memberRepository.findByUsernameAndAgeGreaterThan("Member1", 10);
        //when
        //then
        assertTrue(member.size() == member1.size());
    }

    @Test
    @DisplayName("Dto를 통해 selct 쿼리 전송")
    void start_5() throws Exception {
        //given
        Team teamName1 = new Team("teamName1A");
        Team teamName2 = new Team("teamName1B");

        Member member1 = new Member("MemberA");
        Member member2 = new Member("MemberB");
        member1.setTeam(teamName1);
        member2.setTeam(teamName2);
        memberJpaRepository.save(member1);
        memberJpaRepository.save(member2);

        //when
        List<MemberDto> memberDtos = memberRepository.findMemberDto();
        //then

        memberDtos.stream()
                .forEach(memberdto -> {
                    System.out.println("memberdto.toString() = " + memberdto.toString());
                });

    }

    @Test
    @DisplayName("sql in 절 컬렉션 파라미터 바인딩")
    void start_6() throws Exception {
        //given
        List<String> member = Arrays.asList("MemberA", "test", "username");
        //when
        List<Member> memberNames = memberRepository.findByNames(member);
        //then
        assertTrue(memberNames.size() > 0);
        assertEquals(3, memberNames.size());

    }

    @Test
    @DisplayName("JPA와 Spring-data-jpa 사용시 조회당시 조회 데이터가 없을때")
    void start_7() throws Exception {
        //given
//        assertThrows(NullPointerException.class,()->em.find(Member.class, 1L).getAge());
//        memberJpaRepository.findAll();
        //        Member member = em.find(Member.class, 1L);
        List<Member> noMembersByUsername = memberRepository.findNoMembersByUsername("test");
        assertEquals(noMembersByUsername.size(), 0);
        assertNotNull(noMembersByUsername);
        //when
        //then
    }

    @Test
    @DisplayName("통합 테스트 권장 )) 페이징 JPQL")
    void start_8() throws Exception{
        //given
        //when
        List<Member> findLimitMembers = memberJpaRepository.findByPage(10, 2, 0);
        long totalCount = memberJpaRepository.totalCount(10);
        //then
        assertTrue(totalCount == findLimitMembers.size());
        assertEquals(findLimitMembers.size() , 2);
    }

    @Test
    @DisplayName("JPA를 통해서 벌크 연산 쿼리 실행")
    void start_9() throws Exception{
        //given
        memberJpaRepository.save(new Member("MemberA", 19));
        memberJpaRepository.save(new Member("MemberB", 18));
        memberJpaRepository.save(new Member("MemberC", 11));
        memberJpaRepository.save(new Member("MemberD", 12));
        memberJpaRepository.save(new Member("MemberE", 15));
        //when
        int bulkAgePlusMember = memberJpaRepository.bulkAgePlus(15);
        //then
        assertEquals(3,bulkAgePlusMember);
    }

}