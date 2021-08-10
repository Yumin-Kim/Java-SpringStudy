package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard,Long> {
}
