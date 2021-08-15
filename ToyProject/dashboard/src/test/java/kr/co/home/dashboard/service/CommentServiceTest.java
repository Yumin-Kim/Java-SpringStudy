package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.CommentRepository;
import kr.co.home.dashboard.dao.repository.DashboardRepository;
import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Comment;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.CommentForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.exception.DashboardNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    DashboardRepository dashboardRepository;

    @Mock
    CommentRepository commentRepository;

    private CommentForm commentForm;
    private Member member;
    private Dashboard dashboard;
    private Dashboard dashboard1;
    private Comment comment;
    @BeforeEach
    void configure(){
        member = Member.createMember("name", 12, "email@asd.com", Address.builder().cityCode("cityCode").city("city").detailCity("detailCity").build());
        commentForm = commentForm.of("안녕");
        dashboard = Dashboard.createDashboard("title", "subTitle", "content",member);
        dashboard1 = Dashboard.createDashboard("title", "subTitle", "content",member);
        comment = Comment.createComment(member, dashboard, commentForm);
    }

    @Test
    @DisplayName("createComment pass logic")
    void satrt_1() throws Exception{
        //given
        given(memberRepository.findById(any()))
                .willReturn(Optional.of(member));
        given(dashboardRepository.findById(any()))
                .willReturn(Optional.of(dashboard));
        given(commentRepository.save(any()))
                .willReturn(comment);
        //when
        Req.CommentDto commentDto = commentService.createComment(1L, 2L, commentForm);
        //then
        assertEquals(commentDto.getCommentContent(), "안녕");
        assertEquals(commentDto.getWrittenMember(), member.getName());
    }

    @Test
    @DisplayName("존재 하지 않는 게시판 글 입니다.")
    void start_2() throws Exception{
        given(memberRepository.findById(any()))
                .willReturn(Optional.of(member));
        given(dashboardRepository.findById(any()))
                .willReturn(Optional.empty());
        //when
        //then
        assertThrows(DashboardNotFoundException.class,()->commentService.createComment(1L, 2L, commentForm));

    }

    @Test
    @DisplayName("createComments pass logic")
    void satrt_3() throws Exception{
        //given
        List<Dashboard> dashboards = List.of(dashboard, dashboard1);
        List<CommentForm> of = List.of(CommentForm.of("안녕"), CommentForm.of("안녕하세요"));
        given(memberRepository.findById(any()))
                .willReturn(Optional.of(member));
        given(dashboardRepository.findByIds(any()))
                .willReturn(dashboards);
        //when
        List<Req.CommentDto> comments = commentService.createComments(1L, List.of(2L, 3L), of);
        //then
        assertEquals(comments.size(), 2);
    }

}