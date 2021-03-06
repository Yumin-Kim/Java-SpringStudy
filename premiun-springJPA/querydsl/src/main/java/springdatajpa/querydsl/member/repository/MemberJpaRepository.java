package springdatajpa.querydsl.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;
import springdatajpa.querydsl.member.dto.QMemberTeamDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static springdatajpa.querydsl.domain.QMember.member;
import static springdatajpa.querydsl.domain.QTeam.team;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private JPAQueryFactory jpaQueryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }

    public List<Member> findByUserName(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByUsernames(List<String> usernames) {
        return em.createQuery("select m from Member m where m.username in :usernames", Member.class)
                .setParameter("usernames", usernames)
                .getResultList();
    }

    public List<Member> findByUsernames_querydsl(List<String> usernames) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.username.in(usernames))
                .fetch();
    }

    public List<Member> findAll_querydsl() {
        return jpaQueryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<MemberTeamDto> searchCoditionMember(MemberSearchCondition memberSearchCodition) {
        return jpaQueryFactory.
                select(new QMemberTeamDto(member.id.as("userId"), team.id.as("teamId"), member.username, team.name.as("teamname"), member.age))
                .from(member)
                .leftJoin(member.team, team)
                .where(seachConditionMethodV1(memberSearchCodition))
                .fetch();
    }

    private BooleanBuilder seachConditionMethodV1(MemberSearchCondition memberSearchCodition) {

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (hasText(memberSearchCodition.getUsername())) {
            booleanBuilder.and(member.username.eq(memberSearchCodition.getUsername()));
        }
        if (hasText(memberSearchCodition.getTeamname())) {
            booleanBuilder.and(team.name.eq(memberSearchCodition.getTeamname()));
        }

        if (memberSearchCodition.getAgeGoe() != null) {
            booleanBuilder.and(member.age.goe(memberSearchCodition.getAgeGoe()));
        }
        if (memberSearchCodition.getAgeLoe() != null) {
            booleanBuilder.and(member.age.loe(memberSearchCodition.getAgeLoe()));
        }
        return booleanBuilder;
    }

    private BooleanExpression usernameCodi(String username) {
        return username != null ? member.username.eq(username) : null;
    }

    private BooleanExpression teamnameCodi(String teamname) {
        return teamname != null ? team.name.eq(teamname) : null;
    }

    private BooleanExpression ageGoeCod(Integer agegoe) {
        return agegoe != null ? member.age.goe(agegoe) : null;
    }

    private BooleanExpression ageLoeCod(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }

    private BooleanExpression seachConditionMethodV2(MemberSearchCondition memberSearchCodition) {
        return usernameCodi(memberSearchCodition.getUsername())
                .and(teamnameCodi(memberSearchCodition.getTeamname()))
                .and(ageGoeCod(memberSearchCodition.getAgeGoe()))
                .and(ageLoeCod(memberSearchCodition.getAgeLoe()));
    }

}

