package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Member> findByAge(int age, Pageable pageable);

}
