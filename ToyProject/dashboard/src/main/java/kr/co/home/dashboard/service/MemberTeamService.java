package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.dao.repository.MemberTeamRepository;
import kr.co.home.dashboard.dao.repository.TeamRepository;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.MemberTeam;
import kr.co.home.dashboard.domain.Team;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.MemberNotFoundException;
import kr.co.home.dashboard.exception.TeamNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberTeamService {

    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
    private final MemberTeamRepository memberTeamRepository;

    public Res.MemberTeamDto enterMemberToTeam(Long userId, Long teamId) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new TeamNotFoundException());
        MemberTeam memberTeamRelation = MemberTeam.createRelation(member,team);
        memberTeamRepository.save(memberTeamRelation);
        return new Res.MemberTeamDto(memberTeamRelation);
    }

    public Res.MemberTeamDtos enterMemberToTeams(Long userId, Long[] teamIds) {
        Member member = memberRepository.findById(userId).orElseThrow(() -> new MemberNotFoundException());
        List<Team> findTeams = teamRepository.findByIds(teamIds);
        if(findTeams.size() == 0) throw new TeamNotFoundException();
//        findTeams.stream()
//                .forEach(team -> team.updateMember(member));
//        return new Res.MemberTeamDtos(member);
        return null;
    }

}
