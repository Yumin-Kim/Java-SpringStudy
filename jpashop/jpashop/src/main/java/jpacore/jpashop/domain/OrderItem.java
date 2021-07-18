package jpacore.jpashop.domain;

import jpacore.jpashop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor

public class OrderItem {

    @GeneratedValue
    @Id
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;
    private int orderPrice;

    public OrderItem(int count, int orderPrice) {
        this.count = count;
        this.orderPrice = orderPrice;
    }

    public static OrderItem createOrderItem(int count , int orderPrice) {
        return new OrderItem(count, orderPrice);
    }

    //엔티티에서 dto를 사용하는 경우 모든 계층에서 dto에 의존하게 된다.
//    public void updateOrderItem(OrderItemDto orderItem){
//        if ()
//    }

}
