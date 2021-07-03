package jpacore.jpashop.dto;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private OrderStatus orderStatus;
    private Address address;
    private LocalDateTime orderDate;

    public SimpleOrderDto(Long orderId, String name, OrderStatus orderStatus, Address address, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.name = name;
        this.orderStatus = orderStatus;
        this.address = address;
        this.orderDate = orderDate;
    }

    public SimpleOrderDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName();
        orderDate = order.getOrderDate();
        address = order.getMember().getAddress();
        orderStatus = order.getOrderStatus();
    }
}


