package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Dashboard;
import kr.co.home.dashboard.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DashboardForm {
    @NotNull(message = "대 제목을 입력해주세요")
    @NotBlank(message = "공백입니다")
    private String title;

    @NotNull(message = "소 제목을 입력해주세요")
    @NotBlank(message = "공백입니다")
    private String subTitle;

    @NotNull(message = "내용을 입력해주세요")
    @NotBlank(message = "공백입니다")
    private String content;

    public Dashboard toEntity(Member member) {
        return Dashboard.createDashboard(this.title, this.subTitle, this.content, member);
    }
}
