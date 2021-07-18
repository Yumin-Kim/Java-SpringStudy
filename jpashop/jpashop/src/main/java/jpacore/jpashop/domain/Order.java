package jpacore.jpashop.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders") //
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(Delivery delivery, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public static Order createOrder(Delivery delivery, LocalDateTime orderDate) {
        return new Order(delivery , orderDate,OrderStatus.ORDER);
    }

    public void updateDeliveryInfo(Delivery delivery) {
        this.delivery = delivery;
    }

    public void updateOrderDate(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }

}
