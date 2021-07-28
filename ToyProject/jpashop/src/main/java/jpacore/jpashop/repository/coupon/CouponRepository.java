package jpacore.jpashop.repository.coupon;

import jpacore.jpashop.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    // 쿠폰 정보 저장
    // 쿠폰 정보 수정
    // 쿠폰 정보 하나만 조회
    // 쿠폰 정보 모두 조회
    // 쿠폰 할인 정보 수정


}
