package jpastudy.stduy;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
@Component
public class InitData {

    private final InitService initService;
    @PostConstruct
    public void init(){
        initService.initData();
    }

    @Transactional
    @Component
    static class InitService {

        @PersistenceContext
        private  EntityManager em;

        public void initData(){
            Member member1 = new Member("Member1", 10);
            Team teamA = new Team("TeamA");
            Member member2 = new Member("Member2", 10);
            Team teamB = new Team("TeamB");
            Member member3 = new Member("Member3", 10);
            Team teamC = new Team("TeamC");
            Member member4 = new Member("Member4", 12);
            Team teamD = new Team("TeamD");
            Member member5 = new Member("Member5", 12);
            Team teamE = new Team("TeamE");
            member1.setTeam(teamA);
            member2.setTeam(teamB);
            member3.setTeam(teamC);
            member4.setTeam(teamD);
            member5.setTeam(teamE);
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);
        }

    }
}
