package kr.co.home.dashboard.domain;

import kr.co.home.dashboard.dto.DashboardForm;
import kr.co.home.dashboard.dto.Req;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "dashboard")
public class Dashboard {

    @Id
    @GeneratedValue
    @Column(name = "dashboard_id")
    private Long id;

    private String title;

    private String subTitle;

    private String content;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "dashboard")
    private List<Comment> comments = new ArrayList<>();

    protected Dashboard(String title, String subTitle, String content) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

    public static Dashboard createDashboard(String title, String subTitle, String content, Member member) {
        Dashboard dashboard = new Dashboard(title, subTitle, content);
        dashboard.addMember(member);
        return dashboard;
    }

    private void addMember(Member member) {
        this.member = member;
    }

    public void modifyDashboard(Req.DashboardDto dashboardDto, Member changeMember) {
        if (hasText(dashboardDto.getContent())) {
            this.content = dashboardDto.getContent();
        }
        if (hasText(dashboardDto.getTitle())) {
            this.title = dashboardDto.getTitle();
        }
        if (hasText(dashboardDto.getSubTitle())) {
            this.subTitle = dashboardDto.getSubTitle();
        }
        if (changeMember != null) {
            this.member = changeMember;
        }
    }
}
