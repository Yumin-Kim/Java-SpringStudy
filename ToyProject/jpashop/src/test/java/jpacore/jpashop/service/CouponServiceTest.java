package jpacore.jpashop.service;

import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.CouponDto;
import jpacore.jpashop.repository.dto.ItemDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class CouponServiceTest {

    @Autowired
    CouponService couponService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    void createv1() {
    }

    @Test
    @DisplayName("쿠폰 저장하여 진행")
    void createv2() {
        CouponDto couponForm = createCouponForm();
        Item item = itemRepository.findByArtistAndEtc("artist1", "etc1", "book1");
        jpacore.jpashop.repository.dto.CouponDto couponDto = couponService.createv2(couponForm, item.getId());
        System.out.println("couponDto.toString() = " + couponDto.toString());
        System.out.println("couponDto.toString() = " + couponDto.getItemDto().toString());
        assertThrows(RuntimeException.class,()->couponService.createv2(couponForm,2L));
    }

    private CouponDto createCouponForm() {
        Book saveBook = itemRepository.save(Book.createBook("book1", 100, "company1", "artist1", "etc1"));
        ItemDto itemDto = new ItemDto(saveBook);
        return new CouponDto(itemDto, "쿠폰1", 100, LocalDateTime.now(), LocalDateTime.of(2021, 10, 20, 10, 20));
    }

}