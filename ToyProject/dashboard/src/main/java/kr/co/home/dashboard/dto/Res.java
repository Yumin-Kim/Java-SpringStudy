package kr.co.home.dashboard.dto;

import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.MemberTeam;
import kr.co.home.dashboard.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@Builder
public class Res<T> {

    private String httpStatusCode;
    private T data;

    public static <T> Res<T> isOk(T data) {
        return (Res<T>) Res.builder()
                .httpStatusCode(HttpStatus.OK.toString())
                .data(data)
                .build();
    }

    public static <T>Res<T> isError(T data) {
        return (Res<T>) Res.builder()
                .httpStatusCode(HttpStatus.BAD_REQUEST.toString().toString())
                .data(data)
                .build();
    }

    @Getter
    @Setter
    public static class MemberDto {

        private String name;
        private int age;
        private String email;
        private String city;
        private String cityCode;
        private String detailCity;

        public MemberDto(Member member) {
            this.age = member.getAge();
            this.name = member.getName();
            this.email = member.getEmail();
            this.city = member.getAddress().getCity();
            this.cityCode = member.getAddress().getCityCode();
            this.detailCity = member.getAddress().getDetailCity();
        }
    }


    @Getter
    @Setter
    public static class MemberTeamDto {
        private MemberDto memberInfo;
        private TeamDto teamInfo;

        public MemberTeamDto(MemberTeam memberTeamRelation) {
            memberInfo = new MemberDto(memberTeamRelation.getMember());
            teamInfo = new TeamDto(memberTeamRelation.getTeam());
        }
    }



    @Getter
    @Setter
    public static class TeamDto{
        private String teamName;
        private Integer maxTeamMember;
        private String enterMember;

        public TeamDto(Team team) {
            this.teamName = team.getName();
            this.maxTeamMember = team.getMaxTeamMember();
        }
    }

    @Setter
    @Getter
    @AllArgsConstructor
    public static class MemberTeamDtos {
        private MemberDto memberInfo;
        private List<TeamDto> teamInfo = new ArrayList<>();

        public MemberTeamDtos(Member member, List<Team> teams) {
            this.memberInfo = new MemberDto(member);
            this.teamInfo = teams.stream()
                    .map(TeamDto::new)
                    .collect(toList());
        }
    }
}
