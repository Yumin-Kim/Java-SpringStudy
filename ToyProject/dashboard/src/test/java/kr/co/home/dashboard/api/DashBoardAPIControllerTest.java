package kr.co.home.dashboard.api;

import kr.co.home.dashboard.service.DashboardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = MemberTeamAPIController.class)
class DashBoardAPIControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DashboardService dashboardService;

    @Test
    @DisplayName("updateDashboard pass logic")
    void start_1() throws Exception{
        //given
//        given(dashboardService.updateDashboard());
        //when

        //then
    }

}