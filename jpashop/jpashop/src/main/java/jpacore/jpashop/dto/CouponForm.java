package jpacore.jpashop.dto;

import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.repository.dto.ItemDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@NoArgsConstructor
public class CouponForm {

    private String name;
    private int salePercent;
    private LocalDateTime eventStartPoint;
    private LocalDateTime eventEndPoint;
    private ItemDto itemDto;

    public CouponForm(ItemDto itemDto, String name, int salePercent, LocalDateTime eventStartPoint, LocalDateTime eventEndPoint) {
        this.eventEndPoint = eventEndPoint;
        this.eventStartPoint = eventStartPoint;
        this.name = name;
        this.salePercent = salePercent;
        this.itemDto = itemDto;
    }

    public Coupon toEntity(Item item) {
        return Coupon.createCoupone(name, salePercent, eventStartPoint, eventEndPoint, item);
    }

}
