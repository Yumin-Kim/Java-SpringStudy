package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.TeamRepository;
import kr.co.home.dashboard.domain.Team;
import kr.co.home.dashboard.dto.MergeDto;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.dto.TeamForm;
import kr.co.home.dashboard.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {

    private final TeamRepository teamRepository;

    public List<Res.TeamDto> getTeams(Pageable pageable) {
        List<Team> findAllTeams = teamRepository.findAll(pageable).getContent();
        return findAllTeams.stream()
                .map(Res.TeamDto::new)
                .collect(toList());
    }


    public List<Res.TeamDto> createTeams(List<TeamForm> teamForms) {
        List<Team> teams = teamForms.stream()
                .map(teamForm ->
                        teamRepository.save(teamForm.toEntity())
                )
                .collect(toList());
        return teams.stream()
                .map(Res.TeamDto::new)
                .collect(toList());
    }

    public List<MergeDto.TeamOfMemberDto> getTeamOfMembers(Long teamId, Pageable pageable) {
        List<Team> findMembersByTeam = teamRepository.findById(teamId)
                .map(team -> teamRepository.findMembersById(teamId, pageable))
                .orElseThrow(() -> new TeamNotFoundException());
        return null;
    }

    public List<MergeDto.TeamsOfMemberDtos> getTeamsOfMembers(Long[] teamIds, Pageable pageable) {
        return null;
    }
}
