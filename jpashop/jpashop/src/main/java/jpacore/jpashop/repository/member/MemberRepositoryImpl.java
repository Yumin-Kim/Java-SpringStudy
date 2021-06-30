package jpacore.jpashop.repository.member;

import jpacore.jpashop.dto.UpdateUserInfo;
import jpacore.jpashop.dto.user.DtoUserSearchInfo;
import jpacore.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    @Autowired
    private EntityManager em;


    @Override
    public Optional<List<Member>> findAll(int offset, int limit) {
        List<Member> resultList = em.createQuery("select m from Member m join CouponMember cm on m.id = cm.member", Member.class).getResultList();
        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<Long> findOne(Member user) {
        Member member = em.find(Member.class, user.getId());
        return Optional.ofNullable(member.getId());
    }

    @Override
    public Optional<Member> updateUserInfo(Long id, UpdateUserInfo updateUserInfo) {
        Member member = em.find(Member.class, id);
        member.updateUserInfo(updateUserInfo);
        return Optional.ofNullable(member);
    }

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<List<Member>> searchUserInfo(DtoUserSearchInfo userSearchInfo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> from = query.from(Member.class);
        Join<Object, Object> couponMembers = from.join("couponMembers", JoinType.INNER);
        Join<Object, Object> coupon = couponMembers.join("coupon", JoinType.INNER);

        ArrayList<Predicate> criteria = new ArrayList<>();
        if (userSearchInfo.getDtoMemberInfo().getMemberStatus() != null) {
            Predicate status = cb.equal(from.get("memberStatus"), userSearchInfo.getDtoMemberInfo().getMemberStatus());
            criteria.add(status);
        }
        if (userSearchInfo.getDtoMemberInfo().getAddress() != null) {
            Predicate status = cb.equal(from.get("address"), userSearchInfo.getDtoMemberInfo().getAddress());
            criteria.add(status);
        }
        //QueryDSL 사용해야함 동적 쿼리
        query.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        List<Member> resultList = em.createQuery(query).getResultList();
        return Optional.ofNullable(resultList);
    }
}
