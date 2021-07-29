package springdatajpa.querydsl.member.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.repository.core.EntityMetadata;
import org.springframework.stereotype.Repository;
import springdatajpa.querydsl.domain.Member;
import springdatajpa.querydsl.domain.QMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static springdatajpa.querydsl.domain.QMember.member;

@Repository
public class MemberJpaRepository {

    private final EntityManager em;
    private JPAQueryFactory jpaQueryFactory;

    public MemberJpaRepository(EntityManager em) {
        this.em = em;
        this.jpaQueryFactory = new JPAQueryFactory(em);
    }

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findById(Long userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m ", Member.class).getResultList();
    }

    public List<Member> findByUserName(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Member> findByUsernames(List<String> usernames) {
        return em.createQuery("select m from Member m where m.username in :usernames", Member.class)
                .setParameter("usernames", usernames)
                .getResultList();
    }

    public List<Member> findByUsernames_querydsl(List<String> usernames) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.username.in(usernames))
                .fetch();
    }

    public List<Member> findAll_querydsl() {
        return jpaQueryFactory
                .selectFrom(member)
                .fetch();
    }
}

