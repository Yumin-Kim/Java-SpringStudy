package jpacore.jpashop.repository.order;

import lombok.Data;

@Data
public class OrderItemQueryDto {
    private String itemName;
    private int orderPrice;
    private int count;
    private Long orderId;

    public OrderItemQueryDto(String itemName, int orderPrice, int count, Long orderId) {
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.count = count;
        this.orderId = orderId;
    }
}
