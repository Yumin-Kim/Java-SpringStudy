package jpacore.jpashop.domain;

import jpacore.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon extends BaseEntity {
    @Column(name = "coupon_id")
    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int salePercent;
    private LocalDateTime eventStartPoint;
    private LocalDateTime eventEndPoint;

    @OneToMany(mappedBy = "coupon")
    private List<CouponMember> couponMembers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id",nullable = false)
    private Item item;

    protected Coupon(String name, int salePercent, LocalDateTime eventStartPoint, LocalDateTime eventEndPoint,Item item) {
        this.name = name;
        this.salePercent = salePercent;
        this.eventStartPoint = eventStartPoint;
        this.eventEndPoint = eventEndPoint;
        this.item = item;
    }

    public static Coupon createCoupone(String name , int saleParcent  , LocalDateTime eventStartPoint , LocalDateTime eventEndPoint , Item item) {
        return new Coupon(name, saleParcent, eventStartPoint, eventEndPoint, item);
    }
}
