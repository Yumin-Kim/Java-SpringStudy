package jpacore.jpashop.repository;

import jpacore.jpashop.domain.CouponMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponMemberRepository extends JpaRepository<CouponMember,Long> {
}
