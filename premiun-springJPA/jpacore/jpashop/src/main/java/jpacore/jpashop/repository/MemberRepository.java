package jpacore.jpashop.repository;

import jpacore.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//componentScan를 통해 자동 DI
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //    @PersistenceContext // 스프링 부트에서 PersisistenceContext 애노테이션을 통해서 EntityManager를 DI하게 된다.
//    private final EntityManager em;
// sprintg boot data jpa의 지원하는 기능
    @Autowired
    private final EntityManager em;

//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOnd(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    /**
     * 이름을 통해서 table select
     *
     * @return
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where  m.name = :name ", Member.class).setParameter("name", name).getResultList();
    }

}
