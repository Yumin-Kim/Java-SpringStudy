package kr.co.home.dashboard.api;

import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.MemberTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberTeamAPIController {

    private final MemberTeamService memberTeamService;


    @PostMapping("/memberteam/{userId}/{teamId}")
    public Res enterMemberToTeam(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId) {
        Res.MemberTeamDto enterMember = memberTeamService.enterMemberToTeam(userId, teamId);
        return Res.isOk(enterMember);
    }

    //다중 조회용
    @PostMapping("/memberteams/{userId}/{teamIds}")
    public Res enterMemberToTeams(@PathVariable("userId") Long userId, @PathVariable("teamIds") List<Long> teamIds) {
        Res.MemberTeamDtos enterMember = memberTeamService.enterMembersToTeam(userId, teamIds);
        return Res.isOk(enterMember);
    }
    @GetMapping("/memberteams/{userId}/{teamIds}")
    public Res findAllTeamMember(@PathVariable("userId") Long userId, @PathVariable("teamIds") List<Long> teamIds) {
        Map<String, List<Res.TeamDto>> enterMember = memberTeamService.findAllMemberTeam(userId, teamIds);
        return Res.isOk(enterMember);
    }
}
