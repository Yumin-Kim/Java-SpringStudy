package kr.co.home.dashboard.api;

import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.service.MemberTeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberTeamAPIController {

    private final MemberTeamService memberTeamService;

    @PostMapping("/team/{userId}/{teamId}")
    public Res enterMemberToTeam(@PathVariable("userId") Long userId, @PathVariable("teamId") Long teamId) {
        Res.MemberTeamDto enterMember = memberTeamService.enterMemberToTeam(userId, teamId);
        return Res.isOk(enterMember);
    }

    @PostMapping("/teams/{userId}/{teamIds}")
    public Res enterMemberToTeams(@PathVariable("userId") Long userId, @PathVariable("teamIds") Long[] teamIds) {
        Res.MemberTeamDtos enterMember = memberTeamService.enterMemberToTeams(userId, teamIds);
        //        Res.MemberTeamDto enterMember = memberService.enterMemberToTeam(userId, );
//        return Res.isOk(enterMember);
        return Res.isOk(teamIds);
    }

}
