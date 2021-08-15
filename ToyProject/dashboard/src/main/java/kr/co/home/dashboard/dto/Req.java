package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Comment;
import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

public class Req {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DashboardDto {
        private String title;
        private String subTitle;
        private String content;
        private String writePerson;

        public DashboardDto(Dashboard dashboard) {
            this.title = dashboard.getTitle();
            this.subTitle = dashboard.getSubTitle();
            this.content = dashboard.getContent();
            this.writePerson = dashboard.getMember().getName();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CommentDto {
        private String commentContent;
        private String writtenMember;
        private LocalDateTime createdAt;
        private LocalDateTime modifyLastedAt;

        public CommentDto(Comment comment, Member member, Dashboard dashboard) {
            if (hasText(comment.getContent())) {
                commentContent = comment.getContent();
            }
            if (hasText(member.getName())) {
                writtenMember = member.getName();
            }
            createdAt = LocalDateTime.now();
            modifyLastedAt = LocalDateTime.now();
        }
    }

    @Getter
    @Setter
    public static class MultiCommentsDto {
        private Res.MemberDto MemberInfo;
        private List<DashboardDto> dashboardDto;

    }
}
