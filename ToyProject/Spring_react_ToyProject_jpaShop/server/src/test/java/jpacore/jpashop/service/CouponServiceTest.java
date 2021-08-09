package jpacore.jpashop.service;

import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.repository.CouponRepository;
import jpacore.jpashop.repository.dto.CouponDto;
import jpacore.jpashop.repository.item.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @InjectMocks
    CouponService couponService;

    @Mock
    ItemRepository itemRepository;

    @Mock
    CouponRepository couponRepository;

    Book book = null;
    Coupon coupon = null;

    @BeforeEach
    void config_coupon() {
        book = Book.createBook("book1", 100, "company1", "artist1", "etc1");
        coupon = Coupon.createCoupone("name", 10, LocalDateTime.now(), LocalDateTime.now(), book);
    }

    @Test
    @DisplayName("쿠폰 저장하여 진행")
    void createv2() {
        //given
        given(itemRepository.findById(1L))
                .willReturn(Optional.ofNullable(book));
        when(couponRepository.save(any())).thenReturn(coupon);
        //then
        CouponDto couponDto = couponService.createv2(new CouponDto(coupon, book), 1L);
        //when
        assertEquals(couponDto.getItemDto().getArtist(), book.getArtist());
    }

    @Test
    @DisplayName("쿠폰 저장 로직 실행시 에러 처리")
    void createError() throws Exception {
        //given
        given(itemRepository.findById(1L))
                .willReturn(Optional.ofNullable(null));
        //then
        //when
        assertThrows(IllegalStateException.class,
                ()->couponService.createv2(new CouponDto(coupon, book), 1L),"존재하지 않는 아이템 입니다.");
    }

}