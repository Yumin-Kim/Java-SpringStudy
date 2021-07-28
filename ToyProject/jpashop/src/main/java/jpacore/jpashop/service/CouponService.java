package jpacore.jpashop.service;

import jpacore.jpashop.domain.Coupon;
import jpacore.jpashop.domain.item.Album;
import jpacore.jpashop.domain.item.Book;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.domain.item.Movie;
import jpacore.jpashop.dto.CouponDto;
import jpacore.jpashop.repository.coupon.CouponRepository;
import jpacore.jpashop.repository.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//TODO 개발 비즈니스 요구 사항
// 1. coupon 제거
// 2. coupon 정보 수정
// 3. coupon 소지 사용자 확인
// 4. 조건부 페이징
// 5. 쿠폰 일괄 지급 (벌크 연산)
// 6. 쿠폰 - 멤버에게 지급 CouponMember 할인받은 금액은 null
@Service
@Transactional
public class CouponService {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ItemRepository itemRepository;

    public jpacore.jpashop.repository.dto.CouponDto createv1(CouponDto couponForm){
        //formDTO에 ItemDto를 통해 id 포함
        // 기존 상품이 존재하는지 확인
        itemRepository.findById(couponForm.getItemDto().getId());
        return null;
    }

    /**
     * 상품에 따른 쿠폰 생성
     * @param couponForm RequestBody
     * @param itemId PathVariable
     * @return
     */
    public jpacore.jpashop.repository.dto.CouponDto createv2(CouponDto couponForm, Long itemId){
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalStateException("존재하지 않는 상품입니다."));
        jpacore.jpashop.repository.dto.CouponDto couponDto = null;
        if (item.getClass().getName().contains("Book")) {
            Book book = (Book) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponForm.toEntity(item));
            couponDto = new jpacore.jpashop.repository.dto.CouponDto(saveCoupon,book);
        }
        if (item.getClass().getName().contains("Album")){
            Album album = (Album) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponForm.toEntity(item));
            couponDto = new jpacore.jpashop.repository.dto.CouponDto(saveCoupon,album);
        }
        if (item.getClass().getName().contains("Movie")){
            Movie movie = (Movie) itemRepository.findById(itemId).get();
            Coupon saveCoupon = couponRepository.save(couponForm.toEntity(item));
            couponDto = new jpacore.jpashop.repository.dto.CouponDto(saveCoupon,movie);
        }
        return couponDto;
    }


}
