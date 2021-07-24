package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import jpastudy.stduy.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@Transactional
@Rollback(false)
public class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("엔티티 테스트 코드")
    void start_1() throws Exception{
        //given
        Team team1 = new Team("HelloA");
        Team team2 = new Team("HelloB");

        em.persist(team1);
        em.persist(team2);

        //when
        Member member1 = new Member("member1", 10, team1);
        Member member2 = new Member("member2", 10, team1);
        Member member3 = new Member("member3", 10, team2);
        Member member4 = new Member("member4", 10, team2);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();
        //then

        List<Member> findAllMember = em.createQuery("select m from Member m join m.team  t", Member.class).getResultList();
//        List<Member> findAllMember = em.createQuery("select m from Member m", Member.class).getResultList();
        findAllMember.stream()
                .forEach(member->{
                    System.out.println("******member = " + member);
                    System.out.println("******member.getTeam() = " + member.getTeam());
                });

    }


    @Test
    @DisplayName("Auditing 기능 ")
    void start_2() throws Exception{
        //given
        Member newMember = new Member("Hello");
        memberRepository.save(newMember); //@PrePersist 발생

        Thread.sleep(1000);
        newMember.setUsername("UserName");

        em.flush();
        em.clear();
        //when
        Optional<Member> findMember = memberRepository.findById(newMember.getId());
        findMember.ifPresent(data->{
            System.out.println(data.toString());
            System.out.println(data.getCreateDate());
            System.out.println(data.getLastModifiedDate() );
        });
        //then
    }


}
