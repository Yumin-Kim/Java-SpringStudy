package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.dao.repository.MemberTeamRepository;
import kr.co.home.dashboard.dao.repository.TeamRepository;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.MemberTeam;
import kr.co.home.dashboard.domain.Team;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import kr.co.home.dashboard.exception.MemberteamDuplicateException;
import kr.co.home.dashboard.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class MemberTeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberTeamRepository memberTeamRepository;

    public Res.MemberTeamDto enterMemberToTeam(Long userId, Long teamId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException());
        MemberTeam memberTeamRelation = MemberTeam.createRelation(member, team);
        memberTeamRepository.save(memberTeamRelation);
        return new Res.MemberTeamDto(memberTeamRelation);
    }

    public Res.MemberTeamDtos enterMembersToTeam(Long userId, List<Long> teamIds) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        List<Team> teams = teamRepository.findByIds(teamIds);
        if(teams.size() == 0) throw new TeamNotFoundException();
        teams.stream()
                .forEach(team -> {
                    memberTeamRepository.findByMemberIdAndTeamId(userId,team.getId()).ifPresent(data->{
                        throw new MemberteamDuplicateException();
                    });
                    MemberTeam relation = MemberTeam.createRelation(member, team);
                    memberTeamRepository.save(relation);
                });
        return new Res.MemberTeamDtos(member, teams);
    }

    public Map<String, List<Res.TeamDto>> findAllMemberTeam(Long userId, List<Long> teamIds) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        List<Team> findTeams = teamRepository.findByIds(teamIds);
        if (findTeams.size() == 0) throw new TeamNotFoundException();
        List<MemberTeam> membersToTeam = memberTeamRepository.findByIds(findTeams.stream()
                .map(Team::getId)
                .collect(toList())
        );
        Map<String, List<Res.TeamDto>> teamDtoMap = new HashMap<>();
        membersToTeam.stream()
                .forEach(memberTeam -> {
                    String memberName = memberTeam.getMember().getName();
                    Res.TeamDto teamDto = new Res.TeamDto(memberTeam.getTeam());
                    if (teamDtoMap.containsKey(memberName)) {
                        teamDtoMap.get(memberName).add(teamDto);
                    } else {
                        teamDtoMap.put(memberName, List.of(teamDto));
                    }
                });
        return teamDtoMap;
    }


}
