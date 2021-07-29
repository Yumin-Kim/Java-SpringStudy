package springdatajpa.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.QMember;
import springdatajpa.querydsl.domain.QTeam;
import springdatajpa.querydsl.domain.Team;
import springdatajpa.querydsl.member.dto.MemberDto;
import springdatajpa.querydsl.member.dto.QMemberDto;
import springdatajpa.querydsl.member.dto.UserDto;
import springdatajpa.querydsl.member.repository.MemberRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.*;
import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static springdatajpa.querydsl.domain.QMember.member;
import static springdatajpa.querydsl.domain.QTeam.team;

//@DataJpaTest
@Rollback(false)
@SpringBootTest
@Transactional
public class QuerydslBasicTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    MemberRepository memberRepository;

    JPAQueryFactory queryFactory;

    @BeforeEach
    void beforeEach() {
        queryFactory = new JPAQueryFactory(entityManager);
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
        entityManager.persist(createMember1);
        entityManager.persist(createMember2);
        entityManager.persist(createMember3);
        entityManager.persist(createMember4);
        entityManager.persist(createMember5);
    }

    @Test
    @DisplayName("JPQL")
    void start_1() throws Exception {
        //given
        //when
        Member findMember1 = entityManager.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", "username1")
                .getSingleResult();
        MemberDto findMemberDto = entityManager.createQuery("select new springdatajpa.querydsl.member.dto.MemberDto(m.username,m.age) from Member m where m.username = :username", MemberDto.class)
                .setParameter("username", "username2")
                .getSingleResult();

        //then
        assertEquals(findMember1.getUsername(), "username1");
        assertEquals(findMemberDto.getAge(), 100);
    }

    @Test
    @DisplayName("querydsl 조회")
    void start_2() throws Exception {
        //given
        //when
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QMember qMembermember = new QMember("m12312312"); // 엔티티 조회 있어 고유의 별칭 지정??
        QMember qMembermember1 = new QMember("m12312312"); // 엔티티 조회 있어 고유의 별칭 지정??

        Member findMember1 = jpaQueryFactory.select(qMembermember)
                .from(qMembermember)
                .where(qMembermember.username.eq("username1"))
                .fetchOne();
        Member findMember2 = jpaQueryFactory.select(qMembermember)
                .from(qMembermember1)
                .where(qMembermember1.username.eq("username2"))
                .fetchOne();

        Member findMember3 = jpaQueryFactory.select(member)
                .from(member)
                .where(member.username.eq("username323"))
                .fetchOne();


        //then
        assertEquals(findMember1.getUsername(), "username1");
        assertEquals(findMember2.getUsername(), "username2");
        assertThrows(NullPointerException.class, (Executable) findMember3, "error");
    }

    @Test
    @DisplayName("where 문")
    void start_3() throws Exception {
        //given
        Optional<Member> findMember = Optional.ofNullable(queryFactory.selectFrom(member)
                .where(member.username.eq("username1")
                        .and(member.age.eq(101)))
                .fetchOne());

        Member username1 = queryFactory.selectFrom(member)
                .where(
                        member.username.eq("username1"),
                        member.age.eq(100)
                )
                .fetchOne();
        assertAll(
                () -> assertNull(username1, "현재 존재하지 않습니다")
        );
    }

    @Test
    @DisplayName("결과 조회시")
    void start_4() throws Exception {
        //given
        List<Member> fetch = queryFactory
                .selectFrom(member)
                .fetch();
//        Member fetchOne = queryFactory
//                .selectFrom(QMember.member)
//                .fetchOne();

        Member fetchFirst = queryFactory
                .selectFrom(QMember.member)
                .fetchFirst();
        QueryResults<Member> results = queryFactory
                .selectFrom(member)
                .fetchResults();

        long total = results.getTotal();
        long limit = results.getLimit();
        long offset = results.getOffset();
        List<Member> content = results.getResults();
        //when
        //then
    }

    /*
    회원 정렬
    1. 회원 나이 내림 차순
    2. 회원 이름 올림 차순
    단 2에서 회원 이름이 없으면 마지막에 출력
     */
    @Test
    @DisplayName("JPQL_정렬")
    void start_5() throws Exception {
        //given
        entityManager.persist(new Member(null, 100, null));
        entityManager.persist(new Member("username6", 100, null));
        entityManager.persist(new Member("username7", 100, null));
        List<Member> sortingAge = entityManager.createQuery("select m from Member m where m.age = :age" +
                " order by m.age desc, m.username asc nulls last", Member.class)
                .setParameter("age", 100)
                .getResultList();
        sortingAge.stream()
                .forEach(member1 -> System.out.println("member1.toString() = " + member1.toString()));
        assertEquals(sortingAge.size(), 4);
        assertNull(sortingAge.get(3).getUsername(), "이름이 null입니다");
    }

    @Test
    @DisplayName("querydsl_정렬")
    void start_6() throws Exception {
        //given
        //when
        entityManager.persist(new Member(null, 100, null));
        entityManager.persist(new Member("username6", 100, null));
        entityManager.persist(new Member("username7", 100, null));

        List<Member> sortingAge = queryFactory.selectFrom(member)
                .where(member.age.eq(100))
                .orderBy(member.age.desc(), member.username.asc().nullsLast())
                .fetch();

        //then
        assertEquals(sortingAge.size(), 4);
        assertNull(sortingAge.get(3).getUsername(), "이름이 null입니다");

    }

    @Test
    @DisplayName("spring-data-jpa-페이징")
    void start_7() throws Exception {
        //given
        //when
        PageRequest sortAge = PageRequest.of(1, 2, Sort.by(Sort.Direction.DESC, "age"));

        Page<Member> findSoringOfAge = memberRepository.findAll(sortAge);
        List<Member> content = findSoringOfAge.getContent();
        content.stream()
                .forEach(member1 -> System.out.println("member1.toString() = " + member1.toString()));
        //then
    }

    @Test
    @DisplayName("querydsl_페이징")
    void start_8() throws Exception {
        //given
        List<Member> fetchSortingAgeMember = queryFactory
                .selectFrom(member)
                .orderBy(member.age.desc())
                .offset(1)
                .limit(2)
                .fetch();
        fetchSortingAgeMember.stream()
                .forEach(member1 -> System.out.println("member1.toString() = " + member1.toString()));
        //when

        //then
    }

    @Test
    @DisplayName("querydsl_집합")
    void start_9() throws Exception {
        //given
        //when
        List<Tuple> fetchAgeInfo = queryFactory
                .select(
                        member.age.count(),
                        member.age.sum(),
                        member.age.avg(),
                        member.age.min(),
                        member.age.max()
                ).from(member)
                .fetch();
        //then
        Tuple tuple = fetchAgeInfo.get(0);
        System.out.println("tuple.get() = " + tuple.get(member.age.sum()));
        System.out.println("tuple.get() = " + tuple.get(member.age.avg()));
        System.out.println("tuple.get().min() = " + tuple.get(member.age.min()));
        System.out.println("tuple.get()max() = " + tuple.get(member.age.max()));
        assertEquals(tuple.get(member.age.count()), 5);
//        assertEquals(tuple.get(member.age.sum()),);
    }

    @Test
    @DisplayName("집합 사용 예제")
    void start_10() throws Exception {
        //given
        //when
        List<Tuple> fetchTeam = queryFactory
                .select(team.name, member.age.avg())
                .from(member)
                .join(member.team, team)
                .groupBy(team.name)
                .fetch();

        //then
        Tuple findTeamOfNameTeamA = fetchTeam.get(0);
        Tuple findTeamOfNameTeamB = fetchTeam.get(1);
        Tuple findTeamOfNameTeamC = fetchTeam.get(2);
        System.out.println(findTeamOfNameTeamA.get(team.name));
        System.out.println(findTeamOfNameTeamB.get(team.name));
        System.out.println(findTeamOfNameTeamC.get(team.name));
        System.out.println(findTeamOfNameTeamA.get(member.age.avg()));
        System.out.println(findTeamOfNameTeamB.get(member.age.avg()));
        System.out.println(findTeamOfNameTeamC.get(member.age.avg()));

    }

    @Test
    @DisplayName("querydsl_join ")
    void start_11() throws Exception {
        entityManager.persist(new Member("TeamA", 10, null));
        //given
        List<Member> joinMemberToTeam = queryFactory
                .select(member)
                .from(member)
                .join(member.team, team)
                .fetch();
        joinMemberToTeam.stream()
                .forEach(member1 -> {
                    System.out.println("member1.toString() = " + member1.toString());
                    System.out.println("member1.getTeam().getName() = " + member1.getTeam().getName());
                });

        List<Member> leftJoinMemberToTeam = queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team)
                .fetch();
        leftJoinMemberToTeam.stream()
                .forEach(member1 -> {
                    System.out.println("member1.toString() = " + member1.toString());
                });

        List<Member> rightJoinMemberToTeam = queryFactory
                .selectFrom(member)
                .rightJoin(member.team, team)
                .fetch();

        rightJoinMemberToTeam.stream()
                .forEach(member1 -> {
                    System.out.println("right outer join");
                    System.out.println("member1.toString() = " + member1.toString());
                    System.out.println("member1.toString() = " + member1.getTeam().toString());
                });

        List<Member> theta_join = queryFactory
                .select(member)
                .from(member, team)
                .where(member.username.eq(team.name))
                .fetch();

        theta_join.stream()
                .forEach(member1 -> {
                    System.out.println("from to member team__theta_join");
                    System.out.println("member1.toString() = " + member1.toString());
                });

        List<Member> fetchjoin= queryFactory
                .selectFrom(member)
                .leftJoin(member.team, team).fetchJoin()
                .fetch();
        fetchjoin.stream()
                .forEach(member1 -> {
                    System.out.println("from to member fetch join");
                    System.out.println("member1.toString() = " + member1.toString());
                });

        List<Member> fetch = queryFactory
                .selectFrom(member)
                .join(member.team, team).fetchJoin()
                .fetch();
        fetch.stream()
                .forEach(member1 -> {
                    System.out.println("from to member fetch join");
                    System.out.println("member1.toString() = " + member1.toString());
                    System.out.println("member1.getTeam().getName() = " + member1.getTeam().getName());
                });


    }

    @Test
    @DisplayName("JPQL")
    void start_12() throws Exception {
        //given

        List<Member> teamA1 = memberRepository.findMemberToTeamByUsername("TeamA");
        teamA1.stream()
                .forEach(member1 -> {
                    System.out.println("member1.toString() = " + member1.toString());
                    System.out.println("member1.getTeam() = " + member1.getTeam());
                });

        List<Tuple> teamA = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(member.team, team)
                .on(team.name.eq("TeamA"))
                .fetch();
        teamA.stream()
                .forEach(member1 -> System.out.println("member1 = " + member1));

        //when
        // TODO leftJoin(member.team , team) >> 이와 같이 작성하게되면 member.team의 테이블과 team 즉 QTeam의 고유한 ID가 매칭되어 sql로 변환될시 on member.team_id = team.team_id 같은 문장을 생성하게 되는데
        //  세타 조인(고유의 키로 조인하지 않고 테이블의 유사 문자열 , 숫자를 기반으로 연관 관계를 무시하고 조인하는 행위)를 하게 되면 연관 관계를 무시하고 join하기 때문에 고유한 id를 통해 join을 이룰 필요 없기 떄문에 테이블만 명시해준다.

        entityManager.persist(new Member("TeamA", 10, null));
        entityManager.persist(new Member("TeamB", 10, null));
        entityManager.persist(new Member("TeamC", 10, null));

        List<Tuple> thea_join_member = queryFactory
                .select(member, team)
                .from(member)
                .leftJoin(team)
                .on(member.username.eq(team.name))
                .fetch();

        thea_join_member.stream()
                .forEach(member -> {
                    System.out.println("member = " + member);
                    Member member1 = member.get(0, Member.class);
                    Team team = member.get(1, Team.class);
                    System.out.println("member = " + member);
                    System.out.println("team = " + team);
                });
        //then
    }

    @Test
    @DisplayName("DTO을 통한 조회_querydsl")
    void start_13() throws Exception {
        //given
        List<MemberDto> fetch = queryFactory
                .select(Projections.bean(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        fetch.stream()
                .forEach(memberDto -> System.out.println("memberDto.toString() = " + memberDto.toString()));
        List<MemberDto> fetchMemberDto = queryFactory
                .select(Projections.constructor(MemberDto.class, member.username, member.age))
                .from(member)
                .fetch();
        fetchMemberDto.stream()
                .forEach(memberDto -> System.out.println("memberDto.toString() = " + memberDto.toString()));

        queryFactory
                .select(new QMemberDto(member.username, member.age));

        Map<Integer, Map<String, Team>> transform = queryFactory
                .select(member.username, member.age, team)
                .from(member)
                .leftJoin(member.team, team)
                .transform(groupBy(member.age).as(map(member.username, team)));
        transform.entrySet().stream()
                .forEach(t -> {
                    System.out.println("t.getValue() = " + t.getValue());
                    System.out.println("t.getKey() = " + t.getKey());
                });
        List<UserDto.UserTeamInfo> collect = transform.entrySet()
                .stream()
                .map(e -> new UserDto.UserTeamInfo(e.getKey(),
                        e.getValue().entrySet().stream()
                                .filter(x -> x.getValue() != null)
                                .map((t) -> new UserDto.UserTeamInfo.UserTeamDto(t.getKey(), t.getValue().getName()))
                                .collect(toList())
                ))
                .collect(toList());
        collect.stream()
                .forEach(x -> System.out.println("x = " + x));

        //        fetch1.stream()
//                .forEach(tuple -> {
//                    System.out.println("tuple.get(member.username) = " + tuple.get(member.username));
//                    System.out.println("tuple.get(member.username) = " + tuple.get(member.age));
//                    System.out.println("tuple.get(team) = " + tuple.get(team));
//                });

//        transform.entrySet().stream()
//                .forEach(tra -> System.out.println("tra.getValue() tra.getKey() = " + tra.getKey() + " " + tra.getValue().toString()));
    }
    
    @Test
    @DisplayName("Tuple")
    void start_14() throws Exception{
        //given
        List<Tuple> teamA = queryFactory
                .select(member.username, member.age, team)
                .from(member)
                .innerJoin(member.team, team)
                .fetch();
        //          .forEach(
//                        s -> {
//                            System.out.println("s.get(member.username) = " + s.get(member.username));
//                            System.out.println("s.get(member.age) = " + s.get(member.age));
//                            System.out.println("s.get(team.name) = " + s.get(team.name));
//                        }
//                );
        //when
        //when

        //then
    }
    
}
