package kr.co.home.dashboard.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.MemberForm;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.ExceptionAdvice;
import kr.co.home.dashboard.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = {MemberAPIController.class, ExceptionAdvice.class})
class MemberAPIControllerTest<memberForm> {

    @MockBean
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    List<Res.MemberDto> memberDtos = new ArrayList<>();

    MemberForm memberForm = null;

    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void createMemberDtos() {
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member1 = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));

        Res.MemberDto memberDto = new Res.MemberDto(member);
        Res.MemberDto memberDto1 = new Res.MemberDto(member1);

//        memberForm = new MemberForm(null, 12, null, null, null, null);
        memberForm = new MemberForm("name", 12, "dbals@namver.com", "city", "cityCode", "detailCity");

        memberDtos = List.of(memberDto1, memberDto);
        // TODO 테스트 간에 한글 깨짐 방지
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("getMembersV1")
    void start_1() throws Exception {
        //given
        given(memberService.findMembersInfo(20, 0))
                .willReturn(memberDtos);
        //when
        mockMvc.perform(get("/api/user")
                .param("limit", "20")
                .param("offset", "0")
        )
                //then
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(Res.isOk(memberDtos))));
    }

    @Test
    @DisplayName("createMemberV1")
    void start_2() throws Exception {
        //given
        given(memberService.createMember(any()))
                .willReturn("success");
        //when
        mockMvc.perform(post("/api/user")
                .param("name", memberForm.getName())
                .param("cityCode", memberForm.getCityCode())
                .param("city", memberForm.getCity())
                .param("detailCity", memberForm.getDetailCity())
                .param("email", memberForm.getEmail())
                .param("age", memberForm.getAge() + "")
        )
                .andDo(print());
        //then
    }

    @Test
    @DisplayName("createMemberV1 validation logic")
    void start_3() throws Exception{
        //given
        given(memberService.createMember(any()))
                .willReturn("success");
        //then
        mockMvc.perform(post("/api/user")
        )
        //when
                .andDo(print())
                .andExpect(jsonPath("httpStatusCode", is("400 BAD_REQUEST")))
                .andExpect(jsonPath("data.city[0]", is("city이 공백입니다.")))
                .andExpect(jsonPath("data.city[1]", is("city이 Null 값입니다")))
                .andExpect(jsonPath("data.cityCode[0]", is("cityCode이 Null 값입니다")))
                .andExpect(jsonPath("data.cityCode[1]", is("cityCode이 공백입니다.")))
                .andExpect(jsonPath("data.name[0]", is("name이 Null 값입니다")))
                .andExpect(jsonPath("data.name[1]", is("name이 공백입니다.")))
                .andExpect(jsonPath("data.age[0]", is("age이 Null 값입니다")))
                .andExpect(jsonPath("data.email[0]", is("email이 Null 값입니다")))
                .andExpect(jsonPath("data.detailCity[0]", is("detailCity이 Null 값입니다")))
                .andExpect(jsonPath("data.detailCity[1]", is("detailCity이 공백입니다.")));

    }

    @Test
    @DisplayName("updateMember pass logic")
    void start_4() throws Exception {
        //given
        MemberForm form = new MemberForm(null, 10, null, null, null, null);
        Member member = Member.createMember("김유민", 10, "dbals@name.com", Address.createAddress("city", "cityCode", "detailCode"));
        String s = objectMapper.writeValueAsString(form);
        given(memberService.updateMember(any(),any()))
                .willReturn(new Res.MemberDto(member));
        //when
        mockMvc.perform(put(("/api/user/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(s)
        )
                //then
                .andDo(print())
                .andExpect(jsonPath("data.name", is("김유민")))
                .andExpect(jsonPath("data.age", is(10)))
                .andExpect(jsonPath("data.city", is("city")))
                .andExpect(jsonPath("data.cityCode", is("cityCode")))
                .andExpect(jsonPath("data.detailCity", is("detailCode")))
                .andExpect(jsonPath("data.email", is("dbals@name.com")));
    }

    @Test
    @DisplayName("deletedMember pass logic")
    void start_5() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        given(memberService.deleteMember(any()))
                .willReturn(new Res.MemberDto(member));
        //when
        mockMvc.perform(delete("/api/user/delete/1"))
                //then
                .andDo(print())
                .andExpect(jsonPath("data.name", is("name")));
    }

    @Test
    @DisplayName("enterMemberToTeams pass logic")
    void start_6() throws Exception{
        //given
        mockMvc.perform(post("/api/user/teams/1/1,2,3,4"))
                .andDo(print());
        //when

        //then
    }

}