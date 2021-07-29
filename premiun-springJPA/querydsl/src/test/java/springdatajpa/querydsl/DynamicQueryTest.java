package springdatajpa.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.QMember;
import springdatajpa.querydsl.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static springdatajpa.querydsl.domain.QMember.member;

@DataJpaTest
public class DynamicQueryTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory queryFactory;

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
                .age(100)
                .team(teamB)
                .build();
        Team teamC = Team.builder()
                .name("TeamC")
                .build();

        Member createMember3 = Member.builder()
                .username("username3")
                .age(100)
                .team(teamC)
                .build();
        Member createMember4 = Member.builder()
                .username("username4")
                .age(100)
                .team(teamA)
                .build();
        Member createMember5 = Member.builder()
                .username("username5")
                .age(15)
                .build();
        //when
        em.persist(createMember1);
        em.persist(createMember2);
        em.persist(createMember3);
        em.persist(createMember4);
        em.persist(createMember5);
    }

    @Test
    @DisplayName("동적 쿼리 BooleanBuilder를 통한 해결 방안")
    void start_1() throws Exception{
        //given
        String userCodition = "username1";
        Integer ageCodition = 11;
        //when
        List<Member> findSearchMember = searchmemberV1(userCodition, ageCodition);
        int size = findSearchMember.size();
        System.out.println("size = " + size);
    }

    @Test
    @DisplayName("동적 쿼리 다중 파라미터 사용")
    void start_2() throws Exception{
        //given
        String userCodition = "username1";
        Integer ageCodition = 11;
        //when
        List<Member> findSearchMember = searchmemberV2(userCodition, ageCodition);
        int size = findSearchMember.size();
        //then
    }

    @Test
    @DisplayName("where 조건을 메소드로 뽑은것들을 조합하여 한번에 조건을 재공할 수 있게끔 구현")
    void start_3() throws Exception{
        //given
        String userCodition = "username1";
        Integer ageCodition = 100;
        //when
        List<Member> findSearchMember = searchmember_ComposeEqMethodV3(userCodition, ageCodition);
        int size = findSearchMember.size();
        System.out.println("size = " + size);
        //then
    }

    private List<Member> searchmemberV1(String userCodition, Integer ageCodition) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (userCodition != null) {
            booleanBuilder.and(member.username.eq(userCodition));
        }
        if (ageCodition != null) {
            booleanBuilder.and(member.age.eq(ageCodition));
        }

        return queryFactory
                .selectFrom(member)
                .where(booleanBuilder)
                .fetch();
    }

    private List<Member> searchmemberV2(String userCodition, Integer ageCodition) {
        return queryFactory
                .selectFrom(member)
                .where(usernameEq(userCodition), ageEq(ageCodition))
                .fetch();
    }

    private List<Member> searchmember_ComposeEqMethodV3(String userCodition, Integer ageCodition) {
        return queryFactory
                .selectFrom(member)
                .where(allMemberEq(userCodition, ageCodition))
                .fetch();
    }

    private BooleanExpression ageEq(Integer ageCodition) {
        return ageCodition != null ? member.age.eq(ageCodition) : null;
    }

    private BooleanExpression usernameEq(String userCodition) {
        return userCodition != null ? member.username.eq(userCodition) : null;
    }

    private BooleanExpression allMemberEq(String userNameCodition, Integer ageCodition) {
        return usernameEq(userNameCodition).and(ageEq(ageCodition));
    }
}
