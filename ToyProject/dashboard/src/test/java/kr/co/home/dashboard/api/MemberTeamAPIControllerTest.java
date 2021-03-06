package kr.co.home.dashboard.api;

import kr.co.home.dashboard.service.MemberTeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@WebMvcTest(controllers = {MemberTeamAPIController.class})
class MemberTeamAPIControllerTest {

    @MockBean
    MemberTeamService memberTeamService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("findAllTeamMember pass logic")
    void start_1() throws Exception{
        //given
//        given(memberTeamService.findAllMemberTeam(any()))
//                .willReturn()
        //when

        //then
    }

}