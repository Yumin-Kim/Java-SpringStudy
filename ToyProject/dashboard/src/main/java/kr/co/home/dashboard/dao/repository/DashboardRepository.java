package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard,Long> {
    @Query("select d from Dashboard d where d.id in :dashboardsId")
    List<Dashboard> findByIds(@Param("dashboardsId") List<Long> dashboardsId);
}
