package jpacore.jpashop.domain;

import jpacore.jpashop.domain.enumtype.OrderStatus;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders") //
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity{
    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;


    public static Order createOrder(Delivery delivery, Member member, OrderItem... orderItem) {
        Order order = new Order();
        order.delivery = delivery;
        order.orderStatus = OrderStatus.ORDER;
        order.member = member;
        Arrays.stream(orderItem)
                .forEach(item->{
                    order.addOrderItem(item);
                });
        return order;
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    public void updateDeliveryInfo(Delivery delivery) {
        this.delivery = delivery;
    }

    public void updateOrderDate(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

}
