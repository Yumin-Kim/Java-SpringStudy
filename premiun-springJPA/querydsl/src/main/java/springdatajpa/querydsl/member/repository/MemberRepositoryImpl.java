package springdatajpa.querydsl.member.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.*;
import org.springframework.data.support.PageableExecutionUtils;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.QMember;
import springdatajpa.querydsl.member.dto.MemberSearchCondition;
import springdatajpa.querydsl.member.dto.MemberTeamDto;
import springdatajpa.querydsl.member.dto.QMemberTeamDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static springdatajpa.querydsl.domain.QMember.member;
import static springdatajpa.querydsl.domain.QTeam.team;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private EntityManager em;
    private JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> searchCoditionMember(MemberSearchCondition memberSearchCodition) {
        return jpaQueryFactory.
                select(new QMemberTeamDto(member.id.as("userId"), team.id.as("teamId"), member.username, team.name.as("teamname"), member.age))
                .from(member)
                .leftJoin(member.team, team)
                .where(seachConditionMethodV1(memberSearchCodition))
                .fetch();

    }

    @Override
    public Page<MemberTeamDto> searchCoditionMemberPageCount(MemberSearchCondition memberSearchCodition, Pageable pageable) {
        QueryResults<MemberTeamDto> result = jpaQueryFactory.
                select(new QMemberTeamDto(member.id.as("userId"), team.id.as("teamId"), member.username, team.name.as("teamname"), member.age))
                .from(member)
                .leftJoin(member.team, team)
                .where(seachConditionMethodV1(memberSearchCodition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        long count = result.getTotal();

        return new PageImpl(result.getResults(), pageable, count);
    }

    @Override
    public Page<MemberTeamDto> searchCoditionMemberPageCountCompare(MemberSearchCondition memberSearchCodition, Pageable pageable) {
        List<MemberTeamDto> result = jpaQueryFactory.
                select(new QMemberTeamDto(member.id.as("userId"), team.id.as("teamId"), member.username, team.name.as("teamname"), member.age))
                .from(member)
                .leftJoin(member.team, team)
                .where(seachConditionMethodV1(memberSearchCodition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = jpaQueryFactory.
                select(member)
                .from(member)
                .leftJoin(member.team, team)
                .where(seachConditionMethodV1(memberSearchCodition));

        return PageableExecutionUtils.getPage(result, pageable,()-> countQuery.fetchCount());
    }

    @Override
    public Slice<MemberTeamDto> searchCoditionMemberPageCountSlice(MemberSearchCondition memberSearchCodition, Pageable pageable) {
        return null;
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
