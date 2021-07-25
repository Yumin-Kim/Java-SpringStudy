package jpacore.jpashop.domain;

import jpacore.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class OrderItem extends BaseEntity {

    @GeneratedValue
    @Id
    @Column(name = "order_item_id")
    private Long id;

    private int count;
    private int orderPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(int count, int orderPrice, Item item) {
        this.count = count;
        this.orderPrice = orderPrice;
        this.item = item;
    }

    public static OrderItem createOrderItem(int count, int orderPrice, Item item) {
        return new OrderItem(count, orderPrice,item);
    }

    public void addOrder(Order order){
        this.order = order;
    }

}
