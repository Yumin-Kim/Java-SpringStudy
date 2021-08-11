package kr.co.home.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MergeDto {

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TeamOfMemberDto {
        private Res.TeamDto teamDto;
        private List<Res.MemberDto> memberDtos;
    }

    public static class TeamsOfMemberDtos {
    }
}
