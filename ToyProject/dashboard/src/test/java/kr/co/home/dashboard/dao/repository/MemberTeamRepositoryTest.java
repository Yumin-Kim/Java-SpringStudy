package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.MemberTeam;
import kr.co.home.dashboard.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTeamRepositoryTest {

    @Autowired
    MemberTeamRepository memberTeamRepository;
    
    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void cofigure(){
        Team name1 = Team.createTeam("name1", 1);
        Team name2 = Team.createTeam("name2", 1);
        Team name3 = Team.createTeam("name3", 1);
        Team name4 = Team.createTeam("name4", 1);
        Team name5 = Team.createTeam("name5", 1);
        Team name6 = Team.createTeam("name6", 1);

        Member member1 = Member.createMember("name1", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member2 = Member.createMember("name2", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member3 = Member.createMember("name3", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member4 = Member.createMember("name4", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member5 = Member.createMember("name5", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));

        MemberTeam relation1 = MemberTeam.createRelation(member1, name1);
        MemberTeam relation2 = MemberTeam.createRelation(member2, name1);
        MemberTeam relation3 = MemberTeam.createRelation(member1, name2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        em.persist(member5);

        em.persist(name1);
        em.persist(name2);
        em.persist(name3);
        em.persist(name4);
        em.persist(name5);

        em.persist(relation1);
        em.persist(relation2);
        em.persist(relation3);

    }

    @Test
    @DisplayName("")
    void start_2() throws Exception{
        //given
        List<MemberTeam> findByIds = memberTeamRepository.findByIds(List.of(1L, 2L));
        int size = findByIds.size();
        System.out.println("size = " + size);
        //when

        //then
    }

}