package springdatajpa.querydsl.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@SpringBootTest
//@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("create Membmer Domin")
    void start_1() throws Exception{
        //given
        Member createMember = Member.builder()
                .age(100)
                .username("username")
                .build();
        //when
        em.persist(createMember);
        em.flush();
        em.clear();
        //then
        Member selectOfMember = em.createQuery("select m from Member m", Member.class).getSingleResult();
        assertEquals(selectOfMember.getUsername(),createMember.getUsername());
    }


    @Test
    @DisplayName("Member_Team")
    void start_2() throws Exception{
        //given
        Team teamA = Team.builder()
                .name("TeamA")
                .build();
        Member createMember = Member.builder()
                .username("username1")
                .age(100)
                .team(teamA)
                .build();
        //when

        em.persist(createMember);
        List<Member> findMember = em.createQuery("select m from Member m join fetch m.team t", Member.class).getResultList();
        //then
        List<String> findByMemberName = findMember.stream()
                .map(Member::getUsername)
                .collect(toList());

        Team team = em.find(Team.class, findMember.get(0).getTeam().getId());
        Team teamB = Team.builder()
                .name("TeamB")
                .build();
        findMember.get(0).changeTeam(teamB);
        assertEquals(findMember.get(0).getTeam().getName(),"TeamB");
        //        System.out.println("findByMemberName.size() = " + findByMemberName.size());
        assertEquals(team.getName(),teamA.getName());
    }
    
}