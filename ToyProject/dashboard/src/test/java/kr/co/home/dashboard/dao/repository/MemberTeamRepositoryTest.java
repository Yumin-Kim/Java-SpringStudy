package kr.co.home.dashboard.dao.repository;

import kr.co.home.dashboard.domain.Address;
import kr.co.home.dashboard.domain.Member;
import kr.co.home.dashboard.domain.Team;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTeamRepositoryTest {

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
        Member member3 = Member.createMember("name2", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member4 = Member.createMember("name2", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));
        Member member5 = Member.createMember("name2", 12, "email@email", Address.createAddress("city", "citycode", "DetailCode"));

        

    }
}