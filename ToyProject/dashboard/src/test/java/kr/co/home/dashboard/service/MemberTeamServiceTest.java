package kr.co.home.dashboard.service;

import kr.co.home.dashboard.dao.repository.MemberRepository;
import kr.co.home.dashboard.dao.repository.MemberTeamRepository;
import kr.co.home.dashboard.dao.repository.TeamRepository;
import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.MemberTeam;
import kr.co.home.dashboard.domain.Team;
import kr.co.home.dashboard.dto.Res;
import kr.co.home.dashboard.exception.MemberteamDuplicateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberTeamServiceTest {

    @InjectMocks
    MemberTeamService memberTeamService;

    @Mock
    MemberTeamRepository memberTeamRepository;

    @Mock
    MemberRepository memberRepository;

    @Mock
    TeamRepository teamRepository;

    @Test
    @DisplayName("enterMembersToTeam pass logic")
    void start_1() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Team name1 = Team.createTeam("name1", 1);
        Team name2 = Team.createTeam("name2", 1);
        Team name3 = Team.createTeam("name3", 1);

        MemberTeam relation = MemberTeam.createRelation(member, name1);
        MemberTeam relation1 = MemberTeam.createRelation(member, name2);
        MemberTeam relation2 = MemberTeam.createRelation(member, name3);

        given(memberRepository.findById(any()))
                .willReturn(Optional.of(member));
        given(teamRepository.findByIds(any()))
                .willReturn(List.of(name1, name2,name3));
        given(memberTeamRepository.findByMemberIdAndTeamId(any(), any()))
                .willReturn(Optional.empty());
        given(memberTeamRepository.save(any()))
                .willReturn(relation)
                .willReturn(relation1)
                .willReturn(relation2);
        //when
        Res.MemberTeamDtos memberTeamDtos = memberTeamService.enterMembersToTeam(1L,List.of(2L,3L));
        //then
        assertEquals(memberTeamDtos.getMemberInfo().getName(), member.getName());
        assertEquals(memberTeamDtos.getTeamInfo().size(),3);
    }

    @Test
    @DisplayName("enterMembersToTeam MemberTeamDuplicate validation")
    void start_2() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Team name1 = Team.createTeam("name1", 1);
        Team name2 = Team.createTeam("name2", 1);
        Team name3 = Team.createTeam("name3", 1);

        MemberTeam relation = MemberTeam.createRelation(member, name1);
        MemberTeam relation1 = MemberTeam.createRelation(member, name2);
        MemberTeam relation2 = MemberTeam.createRelation(member, name3);

        given(memberRepository.findById(any()))
                .willReturn(Optional.of(member));
        given(teamRepository.findByIds(any()))
                .willReturn(List.of(name1, name2,name3));
        given(memberTeamRepository.findByMemberIdAndTeamId(any(), any()))
                .willReturn(Optional.of(relation))
                .willReturn(Optional.of(relation1));
        //when
        //then
        assertThrows(MemberteamDuplicateException.class, () -> memberTeamService.enterMembersToTeam(1L, List.of(2L, 3L)));
    }

    @Test
    @DisplayName("findAllMemberTeam pass logic")
    void start_3() throws Exception{
        //given
        Member member = Member.createMember("name", 1, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Team name1 = Team.createTeam("name1", 1);
        Team name2 = Team.createTeam("name2", 1);
        Team name3 = Team.createTeam("name3", 1);

        MemberTeam relation = MemberTeam.createRelation(member, name1);
        MemberTeam relation1 = MemberTeam.createRelation(member, name2);
        MemberTeam relation2 = MemberTeam.createRelation(member, name3);
//        List<MemberTeam> relation11 = List.of(relation, relation1, relation2);
        List<MemberTeam> relation11 = List.of(relation);
        given(teamRepository.findByIds(any()))
                .willReturn(List.of(name1, name2,name3));
        given(memberTeamRepository.findByIds(any()))
                .willReturn(relation11);

        //when
        Map<String, List<Res.TeamDto>> allMemberTeam =
                memberTeamService.findAllMemberTeam(List.of(8L, 9L));
        //then
//        assertEquals(allMemberTeam.get(member.getName()).size() , 3 );
//        assertEquals(allMemberTeam.get(member.getName()).get(0).getTeamName(), name1.getName());
        assertEquals(allMemberTeam.get(member.getName()).size() , 1 );
        assertEquals(allMemberTeam.get(member.getName()).get(0).getTeamName(), name1.getName());
    }

}