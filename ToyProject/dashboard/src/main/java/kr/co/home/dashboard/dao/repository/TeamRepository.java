package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team,Long> {
    @Query("select t from Team t where t.id in :teamIds")
    List<Team> findByIds(@Param("teamIds") List<Long> teamIds);

    @EntityGraph(attributePaths = {"member"})
    @Query("select t from Team t where t.id = :teamId")
    List<Team> findMembersById(Long teamId, Pageable pageable);
}
