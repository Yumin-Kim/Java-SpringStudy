package com.dbals0.dockerspringdeme.domain;

import com.dbals0.dockerspringdeme.repository.MemberRepository;
import com.dbals0.dockerspringdeme.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class domainTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void configure(){
        Member m1  = Member.createMember("name1");
        Member m2  = Member.createMember("name2");
        Member m3  = Member.createMember("name3");
        Team t = Team.createTeam("team1");
        Team t1 = Team.createTeam("team2");
        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);
        teamRepository.save(t);
        teamRepository.save(t1);
        m1.modifiedTeam(t);
        m2.modifiedTeam(t);
        m3.modifiedTeam(t1);
    }

    @Test
    @DisplayName("테스트 케이스 domain")
    void EntityTest() throws Exception{
        // given
        // when
        Member member = memberRepository.findById(1L).get();
        List<Member> findAllMembers = memberRepository.findAll();
        List<Team> findAllTeams = teamRepository.findAll();
        // then
        assertAll(
                () -> assertEquals(member.getName(), "name1"),
                () -> assertEquals(member.getTeam().getName(), "team1"),
                () -> assertEquals(findAllTeams.size(), 2),
                () -> assertEquals(findAllMembers.size(), 3)
        );

    }

}