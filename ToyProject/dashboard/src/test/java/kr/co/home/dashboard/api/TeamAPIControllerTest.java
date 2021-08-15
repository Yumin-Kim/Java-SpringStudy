package kr.co.home.dashboard.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.home.dashboard.CustomCollectorValidator;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.dto.TeamForm;
import kr.co.home.dashboard.exception.ExceptionAdvice;
import kr.co.home.dashboard.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TeamAPIController.class, ExceptionAdvice.class})
class TeamAPIControllerTest {

    @Autowired
    WebApplicationContext wac;

    @MockBean
    TeamService teamService;

    @MockBean
    CustomCollectorValidator customCollectorValidator;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void configure() {
        // TODO 테스트 간에 한글 깨짐 방지
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("createTeams pass logic")
    void start_1() throws Exception {
        //given
        TeamForm name1 = new TeamForm(null, 12222222);
        TeamForm name2 = new TeamForm(null, null);

        List<TeamForm> name11 = List.of(name1);
        String s = objectMapper.writeValueAsString(name11);
        //when
        mockMvc.perform(post("/api/team")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s)
        )
                .andDo(print());
        //then
        verify(customCollectorValidator,times(1)).validate(any(), any());
    }

}