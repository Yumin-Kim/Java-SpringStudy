package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.dto.MemberDto;
import jpastudy.stduy.repository.custom.MemberRepositoryCustom;
import jpastudy.stduy.repository.custom.UsernameOnly;
import jpastudy.stduy.repository.dto.ProjectinosMemberDto;
import jpastudy.stduy.repository.dto.ProjectionsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.lang.annotation.Retention;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    @Query("select m from Member m where m.username = :name")
    Member findMemberByUsername(@Param("name") String name);

    @Query("select new jpastudy.stduy.domain.dto.MemberDto(m.id , m.username , t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findNoMembersByUsername(String names);

    Optional<Member> findNoMemberByUsername(String name);

    // 페이징 쿼리 , 반환 되는 타입에 따라서 다르게 사용될 수 있다.
    Page<Member> findByAge(int age, Pageable pageable);

    Slice<Member> findSliceInterfaceByAge(int age, Pageable pageable);

    List<Member> findListByAge(int age, Pageable pageable);

    //페이징 쿼리 동작후 Count 쿼리를 조작하기!!
    @Query(value = "select m from Member m join fetch m.team t where m.age = :age ",
            countQuery = "select count(m) from Member m")
    Page<Member> findReturnPageByAge(@Param("age") int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    /**
     * TODO @EntityGraph를 통해서 join fetch를 해결할 수 있게 된다.
     * TODO 추가적으로 attributePaths의 fetch join할 엔티티의 명을 쓰게 되면 원하는 엔티티를 fetch join해서 select하게 된다.
     */
    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m where m.username = :username")
    List<Member> findEntityGraphContaionJPQLByUserName(@Param("username") String username);

    @EntityGraph("Member.all")
    List<Member> findEntityGraphNotJPQLByUsername(String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String name);

    // TODO Projections
    List<UsernameOnly> findProjectionsByUsername(String username);

    List<ProjectinosMemberDto> findProjectionsReturnDtoByUsernameAndAge(String username, int age);

    //Native query
    @Query(
            value = "select m.username , t.name as teamName from member m left join team t ",
            countQuery = "select count(*) from member",
            nativeQuery = true
    )
    Page<ProjectionsDto> findByPageProjections(Pageable pageable);

}
