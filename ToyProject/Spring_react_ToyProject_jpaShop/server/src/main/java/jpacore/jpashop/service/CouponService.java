package jpacore.jpashop.service;

import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import jpacore.jpashop.repository.CouponRepository;
import jpacore.jpashop.repository.dto.CouponDto;
import jpacore.jpashop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//TODO 개발 비즈니스 요구 사항
// 1. coupon 제거
// 2. coupon 정보 수정
// 3. coupon 소지 사용자 확인
// 4. 조건부 페이징
// 5. 쿠폰 일괄 지급 (벌크 연산)
// 6. 쿠폰 - 멤버에게 지급 CouponMember 할인받은 금액은 null
@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    private final ItemRepository itemRepository;
    /**
     * 상품에 따른 쿠폰 생성
     * @param couponDtoParam RequestBody
     * @param itemId PathVariable
     * @return
     */
    public CouponDto createv2(CouponDto couponDtoParam, Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));
        CouponDto couponDto = null;
        if (item.getClass().getName().contains("Book")) {
            Book book = (Book) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponDtoParam.toEntity(item));
            couponDto = new CouponDto(saveCoupon,book);
        }
        if (item.getClass().getName().contains("Album")){
            Album album = (Album) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponDtoParam.toEntity(item));
            couponDto = new CouponDto(saveCoupon,album);
        }
        if (item.getClass().getName().contains("Movie")){
            Movie movie = (Movie) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponDtoParam.toEntity(item));
            couponDto = new CouponDto(saveCoupon,movie);
        }
        return couponDto;
    }


}
