package jpacore.jpashop.domain.item;

import jpacore.jpashop.domain.BaseEntity;
import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.OrderItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "dtype")
@EntityListeners(AuditingEntityListener.class)
public abstract class Item extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private String company;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Coupon> coupons = new ArrayList<>();

    Item(String name, int stockPrice, String company) {
        this.name = name;
        this.price = stockPrice;
        this.company = company;
    }

}
