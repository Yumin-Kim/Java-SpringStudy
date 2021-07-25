package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class CouponMember extends  BaseEntity{
    @Id
    @Column(name = "coupon_member_id")
    @GeneratedValue
    private int id;

    private int count;
    private int totalSalePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

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
