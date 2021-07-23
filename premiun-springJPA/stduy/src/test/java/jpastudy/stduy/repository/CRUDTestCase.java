package jpastudy.stduy.repository;

import jpastudy.stduy.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
public class CRUDTestCase {

    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("JPQL사용 전 flush가 될까?")
    void crud_1() throws Exception{
        //given
        Member userName = new Member("flush가 동작하나요??");
        em.persist(userName);

        List<Member> findAllMembers = em.createQuery("select m from Member m", Member.class).getResultList();
        findAllMembers.stream()
                .forEach(findMember->{
                    System.out.println("findMember.toString() = " + findMember.toString());
                });

        //when

        //then
    }

}
