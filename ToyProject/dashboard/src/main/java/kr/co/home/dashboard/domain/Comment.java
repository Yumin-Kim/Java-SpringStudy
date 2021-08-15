package kr.co.home.dashboard.domain;

import kr.co.home.dashboard.dto.CommentForm;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dashboard_id")
    private Dashboard dashboard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    protected Comment(CommentForm commentForm, Member member, Dashboard dashboard) {
        if (hasText(commentForm.getContent())) {
            this.content = commentForm.getContent();
        }
        this.dashboard = dashboard;
        this.member = member;
    }

    public static Comment createComment(Member member, Dashboard dashboard, CommentForm commentForm) {
        return new Comment(commentForm, member, dashboard);
    }

    public void updateContent(CommentForm commentForm) {
        this.content = commentForm.getContent();
    }
}
