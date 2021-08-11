package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamForm {

    @NotNull(message = "이름이 확인해주세요")
    private String name;
    @Min(value = 0,message = "0이하는 생성 불가능합니다.")
    @Max(value = 100,message = "100명 초과하면 안됩니다.")
    @NotNull(message = "인원 제한 수를 확인해주세요")
    private Integer maxTeamMember;

    public Team toEntity() {
        return Team.createTeam(this.name,this.maxTeamMember);
    }
}
