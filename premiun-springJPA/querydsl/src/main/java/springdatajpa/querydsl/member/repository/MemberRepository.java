package springdatajpa.querydsl.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springdatajpa.querydsl.domain.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("select m , t from Member m left join m.team t on t.name = :name")
    List<Member> findMemberToTeamByUsername(@Param("name")String name);

    @Query(
            value = "select m from Member m where m.username in :usernames"
    )
    List<Member> findByUsernames(@Param("usernames") List<String> usernames);

    List<Member> findByUsername(String value);
}
