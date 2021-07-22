package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Retention;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

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


}
