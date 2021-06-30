package jpacore.jpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    @Column(name = "coupon_id")
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int salePercent;
    private LocalDate createdAt;
    private LocalDate deletedAt;

    @OneToMany(mappedBy = "coupon")
    private List<CouponMember> couponMembers = new ArrayList<>();

    public Coupon(String name, int salePercent) {
        this.name = name;
        this.salePercent = salePercent;
        this.createdAt = LocalDate.now();
        this.deletedAt = LocalDate.now();
    }

    public static Coupon createCoupone(String name, int salePercent) {
        return         new Coupon(name, salePercent);
    }
}
