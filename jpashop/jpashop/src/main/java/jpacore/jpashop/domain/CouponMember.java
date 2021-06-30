package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponMember {
    @Id
    @Column(name = "coupon_member_id")
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    private int count;
    private int totalSalePrice;

    public CouponMember(int count, int totalSalePrice, Member member, Coupon coupon) {
        this.count = count;
        this.totalSalePrice = totalSalePrice;
        this.coupon = coupon;
        this.member = member;
    }

    public static CouponMember createCouponeMember(int count, int totalSalcePricce, Member member, Coupon coupon) {
        return new CouponMember(count, totalSalcePricce,member,coupon);
    }
}
