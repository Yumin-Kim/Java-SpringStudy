package jpacore.jpashop.dto;

import jpacore.jpashop.domain.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderForm {

    private Address address;
    private int count;
    private int orderPrice;
    private List<CouponDto> couponForms;
    public OrderForm(Address address, int count, int orderPrice) {
        this.address = address;
        this.count = count;
        this.orderPrice = orderPrice;
    }
    public OrderForm(Address address, int count, int orderPrice, CouponDto... couponForms) {
        this.address = address;
        this.count = count;
        this.orderPrice = orderPrice;
        this.couponForms = Arrays.asList(couponForms);
    }
}
