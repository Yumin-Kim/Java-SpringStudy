package springdatajpa.querydsl;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.Team;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Profile("test")
@RequiredArgsConstructor
public class initMember {

    private final InitMembberService initMembberService;

    @PostConstruct
    public void init(){
        System.out.println("***********@PostConstruct***********");
        initMembberService.init();
    }

    @Component
    static class InitMembberService{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Team teamA = new Team("TeamA");
            Team teamB = new Team("TeamB");

            em.persist(teamA);
            em.persist(teamB);
            for (int i = 0; i < 100; i++) {
                Team selected = i % 2 == 0 ? teamA : teamB;
                em.persist(new Member("member" + i, i,selected));
            }

        }
    }
}
