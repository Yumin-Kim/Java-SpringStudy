package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import jpastudy.stduy.domain.dto.MemberDto;
import jpastudy.stduy.repository.custom.UsernameOnly;
import jpastudy.stduy.repository.dto.ProjectinosMemberDto;
import jpastudy.stduy.repository.dto.ProjectionsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("JpaRepository상속 활용하여 테스트")
    void start_1() throws Exception {
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
    void start_2() throws Exception {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "username"));
//        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC,"username"));
        Page<Member> pageMembers = memberRepository.findByAge(10, pageRequest);
        //then
        List<Member> resultPagingMembers = pageMembers.getContent();
        pageMembers.getContent().stream()
                .forEach(member -> {
                    System.out.println("member.toString() = " + member.toString());
                });
        System.out.println("pageMembers.getTotalElements() = " + pageMembers.getTotalElements());
        //        assertAll(
//                ()->assertEquals(resultPagingMembers.size(),3),
//                ()->assertTrue(pageMembers.getTotalPages() == 1)
//        );
    }

    @Test
    @DisplayName("Slice interface를 활용하여 Paging 구현\n List 반환하는 Paging 메소드는 limit가 정한 수 만큼만 query를 날리게 된다.")
    void start_3() throws Exception {
        //given
        PageRequest pageRequestInfo = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "username"));
        //when
        Slice<Member> members = memberRepository.findSliceInterfaceByAge(10, pageRequestInfo);
        List<Member> membersList = memberRepository.findListByAge(10, pageRequestInfo);
        //then
        List<Member> resultMembers = members.getContent();
        membersList.stream()
                .forEach(member -> {
                    System.out.println("member.getAge() = " + member.getAge());
                    System.out.println("member.getUsername() = " + member.getUsername());
                });

        resultMembers.stream()
                .forEach(resultMember -> {
                    System.out.println("member.toString() = " + resultMember.toString());
                });
        System.out.println("pageMembers.getTotalElements() = " + members.getNumberOfElements());
        System.out.println("pageMembers.getNumber() = " + members.getNumber());
        System.out.println("pageMembers.getPageable() = " + members.getPageable());
    }

    @Test
    @DisplayName("Paging시에 Count query 조작하는 방법")
    void start_4() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.ASC, "username"));
        Page<Member> pagingMember = memberRepository.findReturnPageByAge(10, pageRequest);
        //when

        long totalElements = pagingMember.getTotalElements();
        Pageable pageable = pagingMember.getPageable();
        System.out.println("pageable = " + pageable);
        System.out.println("totalElements = " + totalElements);

        pagingMember.getContent().stream()
                .forEach(member -> {
                    String username = member.getUsername();
                    System.out.println("username = " + username);
                });

        //then
    }

    @Test
    @DisplayName("페이징 처리 후 dto 반환 하기! >> join fetch ")
    void start_5() throws Exception {
        //given
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "username"));
        Page<Member> returnPageByAge = memberRepository.findReturnPageByAge(10, pageRequest);
        //when
        Page<MemberDto> memberDtos = returnPageByAge.map(member -> new MemberDto(member.getId(), member.getUsername(), member.getTeam().getName()));
        //then
        long totalElements = memberDtos.getTotalElements();
        Pageable pageable = memberDtos.getPageable();
        int memberDtosNumberOfElements = memberDtos.getNumberOfElements();

        System.out.println("memberDtosNumberOfElements = " + memberDtosNumberOfElements);
        System.out.println("pageable = " + pageable);
        System.out.println("totalElements = " + totalElements);

    }

    /**
     * Bulk쿼리를 통해서 영속성 컨텍스트에 관리 되어 있는 엔티티가 무시되고 업데이트 되게 된다.
     * bulk 연산을 통해 엔티티의 정보를 수정하는것은 위에 설명한것과 같이 엔티티, 즉 엔티티의 영속성 컨텍스트를 무시하고 진행하기 때문에
     * bulk 연산을 통한 결과를 바로 출력하면 flush 즉 commit되지 않은 상테로 보이기 된다.
     */
    @Test
    @DisplayName("JpaRepository를 통한 bulk 연산!")
    void start_6() throws Exception {
        //given
        memberRepository.save(new Member("MemberA", 19));
        memberRepository.save(new Member("MemberB", 18));
        memberRepository.save(new Member("MemberC", 11));
        memberRepository.save(new Member("MemberD", 12));
        memberRepository.save(new Member("MemberE", 15));
        //when
        int bulkAgePlusMember = memberRepository.bulkAgePlus(15);
        //then
        assertEquals(3, bulkAgePlusMember);

    }

    /**
     * JPQL 작성후 작성된 메소드가 실행되기 전 상황에 모든 엔티티는 flush가 된다
     */

    @Test
    @DisplayName("bulk 쿼리 후 문제 발생")
    void start_7() throws Exception {
        //given

        memberRepository.save(new Member("MemberA", 19));
        memberRepository.save(new Member("MemberB", 18));
        memberRepository.save(new Member("MemberC", 11));
        memberRepository.save(new Member("MemberD", 12));
        Member memberE = new Member("MemberE", 15);
        Member saveMember = memberRepository.save(memberE);

        //flush 발생 쓰기지연 SQL >> DB로 전송
        int bulkAgePlusMember = memberRepository.bulkAgePlus(15);
        //TODO Bulk연산을 하는 메소드 이며 해당 Bulk 연산은 기존의 영속성 컨텍스트의 엔티티를 무시하고 쿼리를 진행하게 된다.고로 쓰기지연 SQL저장소에도 Update Query가 남지 않았을것이다.
        //엔티티 무시하고 Update SQL 발생 >> DB로 전송
//        List<Member> findMemberE = memberRepository.findByNames(Arrays.asList("MemberE"));
        // 쓰기 지연 SQL 저장소를 확인하지만 아무 쿼리도 없음
        Member memberE1 = memberRepository.findMemberByUsername("MemberE");
        //TODO em.clear()를 하지 않게 되면 1차 캐시를 비우지 못하게 된다. JPQL은 바로 SQL문으로 변환되며 바로 DB를 조회하게 된다. 하지만 사용자가 원하는
        //TODO 원하는 정보가 보이지 않고 업데이트 이전의 정보를 확인할 수 있다.JPQL특성상 1차 캐시를 확인하지 않고 바로 쿼리를 실행하는 부분을 인지하고 봐야한다.
        //TODO 추가적으로 바로 쿼리가 실행됨에 따라 1차 캐시의 존재하는 엔티티정보와 JPQL의 쿼리 결과의 엔티티 값의 동일성을 보장하기 위해 JPQL실행 전 flush()되는것을 인지하여하 한다.
        //TODO 현재 코드는 Update 쿼리가 (SQL 저장소에 없으며 flush 정보가 없기때문에)JPA가 인식을 못하게 되고 해당 Member는 Update되지 않은 정보를 가지고 오게 된다.

        //TODO 번외로 1차 캐시와 DB의 정보가 다르다고 가정하며 JPQL select문을 실행하게 되면 JPQL은 DB의 정보를 가지고 오지만 1차 캐시에 있는 정보가 확인 된다.(영속성 컨텍스트의 동일성 때문)

        //TODO 인프런 질문 참고 (https://www.inflearn.com/questions/35138)

        em.clear();
        Member member = em.find(Member.class, memberE.getId());
        //TODO 현재 em.clear()를 한 상태이며 1차 캐시 비우게 된다. 비우게 되면서 select query가 한번 더 실행되는 것을 볼 수 있다.
        //TODO em.clear()를 생략 하지 않게 된다면 select 쿼리가 실행 되지 않게 되고 쿼리가 실행되지 않는다말은 1차 캐시에서 가지고 온다는 것을 알 수 있다.

//        System.out.println("findMemberE.get(0).toString() = " + findMemberE.get(0).toString());
        System.out.println("memberE1.toString() = " + memberE1.toString());
        System.out.println("username = " + member.getAge());
        //        System.out.println("member.toString() = " + member.toString());
        assertEquals(3, bulkAgePlusMember);
    }

    @Test
    @DisplayName("@EntityGraph 기초 버전")
    void start_8() throws Exception {
        //given
        Team teamA = new Team("TeamA");
        Team teamB = new Team("TeamB");

        Member memberA = new Member("MemberA");
        Member memberB = new Member("MemberB");

        memberA.setTeam(teamA);
        memberB.setTeam(teamB);

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // 1차 캐시 초기화
        em.flush();
        em.clear();
        //when
//        List<Member> findAllMembers = memberRepository.findAll();
//        List<Member> fetchJoinMember = em.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
        List<Member> fetchJoinMember = memberRepository.findAll();
        //then

//        findAllMembers.stream()
//                .forEach(member->{
//                    System.out.println(member.getUsername());
//                    System.out.println(member.getTeam().getClass());
//                });
        fetchJoinMember.stream()
                .forEach(member -> {
                    System.out.println("member.getUsername() = " + member.getUsername());
                    System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
                    System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
                });
    }

    @Test
    @DisplayName("@EntityGraph 활용 버전 - 메소드 쿼리 , @Query 사용")
    void start_9() throws Exception {
        //given
        List<Member> member1 = memberRepository.findEntityGraphNotJPQLByUsername("Member1");
//        List<Member> member1 = memberRepository.findEntityGraphContaionJPQLByUserName("Member1");
        System.out.println("member1.get(0).getTeam().getClass() = " + member1.get(0).getTeam().getClass());
        System.out.println("member1.get(0).getUsername() = " + member1.get(0).getUsername());
        /**
         //when
         System.out.println("member1.get(0).getTeam().getClass() = " + member1.get(0).getTeam().getClass());
         System.out.println("member1.get(0).getUsername() = " + member1.get(0).getUsername());
         * ////////////////////////////////////////////////
         *  select
         *         member0_.member_id as member_i1_0_0_,
         *         team1_.team_id as team_id1_1_1_,
         *         member0_.age as age2_0_0_,
         *         member0_.team_id as team_id4_0_0_,
         *         member0_.username as username3_0_0_,
         *         team1_.name as name2_1_1_
         *     from
         *         member member0_
         *     left outer join
         *         team team1_
         *             on member0_.team_id=team1_.team_id
         *     where
         *         member0_.username=
         *
         * ////////////////////////////////////////////////
         *
         *    select
         *         member0_.member_id as member_i1_0_0_,
         *         team1_.team_id as team_id1_1_1_,
         *         member0_.age as age2_0_0_,
         *         member0_.team_id as team_id4_0_0_,
         *         member0_.username as username3_0_0_,
         *         team1_.name as name2_1_1_
         *     from
         *         member member0_
         *     left outer join
         *         team team1_
         *             on member0_.team_id=team1_.team_id
         *     where
         *         member0_.username=
         *
         * ////////////////////////////////////////////////
         *
         */
        //then
    }

    /**
     * 영속성 컨텍스트가 생성될 때, 향후 변경 감지를 위해서 원본을 복사해서 만들어두는 객체가 바로 스냅샷입니다.
     * 해당 query는 readOnly를 통해서 변경 감지 , 스냅샷 자체를 만들지 않아 오직 read만 가능한 상태로 hibernate가 최적화 하게 된다.
     */
    @Test
    @DisplayName("@QueryHints 사용하여 ReadOnly속성을 추가하여 최적화 하는 작업")
    void start_10() throws Exception{
        //given
        Member member1 = memberRepository.findReadOnlyByUsername("Member1");
        member1.setAge(100);
        member1.setUsername("MemberReadOnly");
        //when
        em.flush();
        em.clear();
        //then
        System.out.println("member1.toString() = " + member1.toString());
    }
    
    @Test
    @DisplayName("확장 기능 - 사용자 정의 인터페이스 메소드!")
    void start_11() throws Exception{
        //given
        List<Member> allCustom = memberRepository.findAllCustom();
        //when
        allCustom.stream()
                .forEach(member->{
                    System.out.println("member.getUsername() = " + member.getUsername());
                    System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
                });
        //then
    }

    @Test
    @DisplayName("테이블 조회시 개발자가 정의한 정보만 가지고 오기")
    void start_12() throws Exception{
        //given
        //when
        List<UsernameOnly> member1 = memberRepository.findProjectionsByUsername("Member1");
        List<ProjectinosMemberDto> member11 = memberRepository.findProjectionsReturnDtoByUsernameAndAge("Member1", 10);
        //then
        assertEquals(member1.size(), 1);
        assertEquals(member1.get(0).getUsername(),"Member1");
        assertEquals(member11.size(),1);
        assertEquals(member11.get(0).getTeam().getName(),"TeamA");
    }

    @Test
    @DisplayName("네이티브 쿼리 페이징 처리")
    void start_13() throws Exception{
        //given
        PageRequest of = PageRequest.of(0, 2);
        Page<ProjectionsDto> byPageProjections = memberRepository.findByPageProjections(of);
        byPageProjections.getContent().stream()
                .forEach(member->{
                    System.out.println("member.getUsername() = " + member.getUsername());
                    System.out.println("member.getUsername() = " + member.getTeamName());
                });
        //when

        //then
    }


}
