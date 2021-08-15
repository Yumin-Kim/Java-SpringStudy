package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.DashboardRepository;
import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.Req;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @InjectMocks
    DashboardService dashboardService;

    @Mock
    MemberRepository memberRepository;
    @Mock
    DashboardRepository dashboardRepository;

    @Test
    @DisplayName("updateDashboard pass logic")
    void start_1() throws Exception {
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Dashboard dashboard = Dashboard.createDashboard("title", "subTitle", "content", member);
        given(dashboardRepository.findById(any()))
                .willReturn(Optional.of(dashboard));
        //when
        Req.DashboardDto dashboardDto = dashboardService.updateDashboard(new Req.DashboardDto("Hello", null, null, null), 1L);
        //then
    }

}