package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long> {

    @Query("select distinct mt from MemberTeam mt " +
            "join fetch mt.member m " +
            "join fetch mt.team t " +
            "where m.id in :userIds")
    List<MemberTeam> findByIds(@Param("userIds") List<Long> userId);

    @Query("select mt from MemberTeam mt " +
            "join fetch mt.member m " +
            "join fetch mt.team t " +
            "where m.id = :userId and" +
            "where t.id = :teamId ")
    Optional<MemberTeam> findByMemberIdAndTeamId(@Param("userId") Long userId,@Param("teamId") Long teamId);
}
