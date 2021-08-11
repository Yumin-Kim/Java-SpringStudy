package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.MemberTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTeamRepository extends JpaRepository<MemberTeam,Long> {
}
