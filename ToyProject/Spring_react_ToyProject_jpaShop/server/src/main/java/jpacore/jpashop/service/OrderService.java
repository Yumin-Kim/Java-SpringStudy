package jpacore.jpashop.service;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.domain.enumtype.DeliveryStatus;
import jpacore.jpashop.domain.item.Item;
import jpacore.jpashop.dto.CouponDto;
import jpacore.jpashop.dto.OrderForm;
import jpacore.jpashop.dto.OrderSearchCondition;
import jpacore.jpashop.repository.CouponMemberRepository;
import jpacore.jpashop.repository.dto.OrderDto;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import jpacore.jpashop.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@RequiredArgsConstructor
//TODO 개발 비즈니스 요구사항 - OrderService
// 1. 페이징 처리를 통한 Select
// 2. 주문취소 - 주문 취소 쿠폰 사용 여부 확인후 쿠폰 반환 및 사용자 장고에 다시 돈 제공
// 3. 동적 쿼리 - 조건부 join을 통해 select문 where in 사용
// 4. 주문시 쿠폰 사용 여부 확인 후 할인
// 5. 주문시 쿠폰 사용 여부 파악후 비즈니스 로직 실행
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final CouponMemberRepository couponMemberRepository;

    /**
     * 단일 구매
     *
     * @param itemId    RequestParam
     * @param userId    PathVariable
     * @param orderForm OrderForm RequestBody
     * @return
     */
    public OrderDto createV1(Long itemId, Long userId, OrderForm orderForm) {
        Member member = memberRepository.findStorageById(userId).orElseThrow(
                getIllegalStateExceptionSupplier("존재 하지 않는 아이디 입니다.")
        );
        Item item = itemRepository.findById(itemId).orElseThrow(getIllegalStateExceptionSupplier("존재 하지 않은 상품입니다."));
        Delivery delivery = Delivery.builder()
                .status(DeliveryStatus.START)
                .address(orderForm.getAddress())
                .build();
        calculatePrice(member.getMoneyStorage(), item, orderForm.getCount(), orderForm.getOrderPrice(),orderForm.getCouponForms());
        OrderItem orderItem = OrderItem.createOrderItem(orderForm.getCount(), orderForm.getOrderPrice(), item);
        Order order = Order.createOrder(delivery, member, orderItem);
        return new OrderDto(orderRepository.save(order));
    }

    public List<OrderDto> selectAllItem(Long itemId, Long userId, OrderSearchCondition orderSearchCondition) {



        return null;
    }

//    public OrderDto searchV1(){
//
//    }



    private void calculatePrice(MoneyStorage moneyStorage, Item item, int count, int orderPrice, List<CouponDto> couponForms) {
        if (couponForms != null) {
            if (couponForms.size() > 0) {
                List<Long> couponIds = couponForms.stream()
                        .map(CouponDto::getId)
                        .collect(toList());
//                List<CouponMember> findIdCouponMember = couponMemberRepository.findCouponInfoByIds(couponIds);
//                if(findIdCouponMember.size() < 0){
//                    throw new IllegalStateException("소지 하지 않은 쿠폰입니다. 다시 주문하세요");
//                }
                //coupon_m = 모든 정보 와 coupon_쿠폰 할인율 정보 필요
//                findIdCouponMember.stream()
//                        .map(CouponMember::getCoupon)
//                        .map(Coupon::get)
            }
        }
        int totalPrice = moneyStorage.getStoragePoint() - (item.getPrice() * count);
        if (totalPrice < 0) {
            throw new IllegalStateException("잔액이 부족합니다!");
        } else {
            moneyStorage.modifyStoragePoint(totalPrice);
        }
    }

    private Supplier<IllegalStateException> getIllegalStateExceptionSupplier(String message) {
        return () -> new IllegalStateException(message);
    }

}
