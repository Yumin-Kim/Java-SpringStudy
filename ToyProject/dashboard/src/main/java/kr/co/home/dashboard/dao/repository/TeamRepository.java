package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
}
