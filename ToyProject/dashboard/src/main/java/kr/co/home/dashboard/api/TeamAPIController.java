package kr.co.home.dashboard.api;

import kr.co.home.dashboard.CustomCollectorValidator;
import kr.co.home.dashboard.ValidList;
import kr.co.home.dashboard.dto.MergeDto;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.dto.TeamForm;
import kr.co.home.dashboard.service.TeamService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO
// 1.팀만 출력
// 2.클라이언트에서 조회한 팀과 관련된 인원 조회
// 3.team 생성
@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
@Slf4j
public class TeamAPIController {

    private final TeamService teamService;
    @Autowired
    private CustomCollectorValidator customCollectorValidator;

    @GetMapping
    public Res getTeams(Pageable pageable) {
        List<Res.TeamDto> teamDtos = teamService.getTeams(pageable);
        return Res.isOk(teamDtos);
    }

    @GetMapping("/{teamId}")
    public Res getTeamOfMember(Pageable pageable , @PathVariable("teamId") Long teamId) {
        List<MergeDto.TeamOfMemberDto> teamsOfMembers = teamService.getTeamOfMembers(teamId,pageable);
        return Res.isOk(teamsOfMembers);
    }

    @GetMapping("/{teamIds}")
    public Res getTeamsOfMembers(Pageable pageable, @PathVariable("teamIds") Long[] teamIds) {
        List<MergeDto.TeamsOfMemberDtos> teamsOfMemberDtos = teamService.getTeamsOfMembers(teamIds,pageable);
        return Res.isOk(teamIds);
    }

    @PostMapping
    public Res createTeams(@Valid @RequestBody List<TeamForm> teamForms, BindingResult bindingResult) throws BindException {
        customCollectorValidator.validate(teamForms, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        List<Res.TeamDto> teamDto = teamService.createTeams(teamForms);
        return Res.isOk(teamForms);
    }

}
