package springdatajpa.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Modifying;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.QMember;
import springdatajpa.querydsl.domain.Team;
import springdatajpa.querydsl.member.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static springdatajpa.querydsl.domain.QMember.member;

@DataJpaTest
public class BatchQueryTest {
    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void beforeEach() {
        queryFactory = new JPAQueryFactory(em);
        Team teamA = Team.builder()
                .name("TeamA")
                .build();

        Member createMember1 = Member.builder()
                .username("username1")
                .age(11)
                .team(teamA)
                .build();
        Team teamB = Team.builder()
                .name("TeamB")
                .build();
        Member createMember2 = Member.builder()
                .username("username2")
                .age(20)
                .team(teamB)
                .build();
        Team teamC = Team.builder()
                .name("TeamC")
                .build();

        Member createMember3 = Member.builder()
                .username("username3")
                .age(30)
                .team(teamC)
                .build();
        Member createMember4 = Member.builder()
                .username("username4")
                .age(40)
                .team(teamA)
                .build();
        Member createMember5 = Member.builder()
                .username("username5")
                .age(50)
                .build();
        //when
        em.persist(createMember1);
        em.persist(createMember2);
        em.persist(createMember3);
        em.persist(createMember4);
        em.persist(createMember5);
    }

    @Test
    @DisplayName("bulk update")
    void start_1() throws Exception{
        //given
        String value = "비회원으로 변경되었..";
        queryFactory
                .update(member)
                .set(member.username, value)
                .where(member.age.lt(28))
                .execute();
        em.clear();
        //when
        List<Member> findUpdateMembers = em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", value)
                .getResultList();

//        List<Member> findUpdateMembers = memberRepository.findByUsername(value);

        //then
        assertEquals(findUpdateMembers.size(), 2);
        assertEquals(findUpdateMembers.get(0).getUsername(),value);
        assertEquals(findUpdateMembers.get(1).getUsername(),value);
    }

    @Test
    @DisplayName("더하기 bulk update")
    void start_2() throws Exception{
        //given
        queryFactory
                .update(member)
                .set(member.age, member.age.add(10))
                .execute();
        //when

        //then
    }
    
    @Test
    @DisplayName("bulk delete")
    void start_3 () throws Exception{
        //given
        queryFactory
                .delete(member)
                .where(member.age.lt(20))
                .execute();
        //when
        
        //then
    }
    
}
