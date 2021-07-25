package jpacore.jpashop.dto;

import jpacore.jpashop.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderForm {

    private Address address;
    private int count;
    private int orderPrice;

    public OrderForm(Address address, int count, int orderPrice) {
        this.address = address;
        this.count = count;
        this.orderPrice = orderPrice;
    }
}
