package kr.co.home.dashboard.api;

import kr.co.home.dashboard.dto.DashboardForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.DashboardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
public class DashBoardAPIController {

    private final DashboardService dashboardService;

    @GetMapping
    public Res findAllDashboard(Pageable pageable) {
        List<Req.DashboardDto> findAlldashboards = dashboardService.findAllDashboard(pageable);
        return Res.isOk(findAlldashboards);
    }

    @PostMapping("/{userId}")
    public Res createDashboard(
            @Valid DashboardForm dashboardForm,
            @PathVariable("userId") Long userId) {
        Req.DashboardDto dashboardDto = dashboardService.createDashboard(dashboardForm, userId);
        return Res.isOk(dashboardDto);
    }

    @PutMapping("/{dashboardId}")
    public Res updateDashboard(Req.DashboardDto modifyDashboard, @PathVariable("dashboardId") Long dashboardId) {
        Req.DashboardDto dashboardDto = dashboardService.updateDashboard(modifyDashboard, dashboardId);
        return Res.isOk(dashboardDto);
    }



}
