package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.CommentRepository;
import kr.co.home.dashboard.dao.repository.DashboardRepository;
import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.domain.Comment;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.dto.CommentForm;
import kr.co.home.dashboard.dto.Req;
import kr.co.home.dashboard.exception.CommentFormsInputException;
import kr.co.home.dashboard.exception.CommentNotFoundException;
import kr.co.home.dashboard.exception.DashboardNotFoundException;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final MemberRepository memberRepository;
    private final DashboardRepository dashboardRepository;
    private final CommentRepository commentRepository;

    public Req.CommentDto createComment(Long userId, Long dashboardId, CommentForm commentForm) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElseThrow(() -> new DashboardNotFoundException());
        Comment saveComment = commentRepository.save(Comment.createComment(member, dashboard, commentForm));
        return new Req.CommentDto(saveComment, member, dashboard);

    }

    public List<Req.CommentDto> createComments(Long userId, List<Long> dashboardsId, List<CommentForm> commentsform) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        List<Dashboard> dashboards = dashboardRepository.findByIds(dashboardsId);
        List<Comment> comments = new ArrayList<>();
        if (dashboards.size() != dashboardsId.size()) {
            throw new  DashboardNotFoundException();
        }
        if (dashboards.size() != commentsform.size()) {
            throw new CommentFormsInputException();
        }
        for (int i = 0; i < dashboards.size(); i++) {
            Comment comment = Comment.createComment(member, dashboards.get(i), commentsform.get(i));
            comments.add(comment);
            commentRepository.save(comment);
        }
        return comments.stream()
                .map(comment -> new Req.CommentDto(comment, member, null))
                .collect(Collectors.toList());
    }

    public List<Req.CommentDto> findComment(Long dashboardId) {
        Dashboard dashboard = dashboardRepository.findById(dashboardId).orElseThrow(() -> new DashboardNotFoundException());
        List<Comment> comments = commentRepository.findByDashBoardId(dashboardId);
        if (comments.size() == 0) throw new CommentNotFoundException();
        return comments.stream()
                .map(comment -> new Req.CommentDto(comment, comment.getMember(), comment.getDashboard()))
                .collect(Collectors.toList());
    }

    public Req.CommentDto modifyComment(Long commentId, CommentForm commentForm) {
        Comment comment = commentRepository.findMemberById(commentId).orElseThrow(() -> new CommentNotFoundException());
        comment.updateContent(commentForm);
        return new Req.CommentDto(comment, comment.getMember(), null);
    }
}
