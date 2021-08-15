package kr.co.home.dashboard.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.home.dashboard.CustomCollectorValidator;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Comment;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.CommentForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {CommentAPIController.class})
class CommentAPIControllerTest {

    @MockBean
    CommentService commentService;

    @MockBean
    CustomCollectorValidator customCollectorValidator;

    @Autowired
    WebApplicationContext wac;

    ObjectMapper objectMapper = new ObjectMapper();

    MockMvc mockMvc;

    Member member;
    Dashboard dashboard;
    CommentForm content;
    Comment comment;
    Req.CommentDto commentDto;

    @BeforeEach
    void configure() {
        // TODO 테스트 간에 한글 깨짐 방지
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
        member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        dashboard = Dashboard.createDashboard("title", "subTitle", "dashBoard content", member);
        content = CommentForm.of("content");
        comment = Comment.createComment(member, dashboard, content);
        commentDto = new Req.CommentDto(comment, member, dashboard);
    }

    @Test
    @DisplayName("create Comment")
    void start_1() throws Exception{
        //given
        given(commentService.createComment(any(), any(), any()))
                .willReturn(commentDto);
        //when
        mockMvc.perform(post("/api/comment/1/2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(content))
                        .param("content", "content")
        )
                //then
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.commentContent", is("content")))
                .andExpect(jsonPath("data.writtenMember", is(member.getName())));
    }

    @Test
    @DisplayName("create comments multi param")
    void start_2() throws Exception{
        CommentForm comment1 = CommentForm.of("안녕");
        CommentForm comment2 = CommentForm.of("안녕하세요");
        Comment commentEntity1 = Comment.createComment(member, dashboard, comment1);
        Comment commentEntity2 = Comment.createComment(member, dashboard, comment2);
        Req.CommentDto commentDto = new Req.CommentDto(commentEntity1, commentEntity1.getMember(), null);
        Req.CommentDto commentDto1 = new Req.CommentDto(commentEntity2, commentEntity2.getMember(), null);
        //given
        List<Req.CommentDto> multiComments = List.of(commentDto, commentDto1);
        given(commentService.createComments(any(), any(), any()))
                .willReturn(multiComments);
        mockMvc.perform(post("/api/comments/1/2,3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(List.of(comment1, comment2)))
        )
                .andDo(print())
                .andExpect(jsonPath("data[0].commentContent", is("안녕")))
                .andExpect(jsonPath("data[0].writtenMember", is(member.getName())))
                .andExpect(jsonPath("data[1].commentContent", is("안녕하세요")))
                .andExpect(jsonPath("data[1].writtenMember", is(member.getName())));
        //when
        //then
    }


}