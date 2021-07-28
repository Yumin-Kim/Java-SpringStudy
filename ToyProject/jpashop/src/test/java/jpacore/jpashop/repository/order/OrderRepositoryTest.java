package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Delivery;
import jpacore.jpashop.domain.enumtype.DeliveryStatus;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.repository.item.ItemRepository;
import jpacore.jpashop.repository.member.MemberRepository;
import jpacore.jpashop.repository.order.old.Old_OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
class OrderRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ItemRepository itemRepository;


    @Test
    @DisplayName("페이징 기반 조건 X")
    void itemRepo_1() throws Exception{
        //given

        //when

        //then
    }

    @Test
    @DisplayName("주문 취소 - 주문 취소시 쿠폰 반환")
    void itemRepo_2() throws Exception{
        //given

        //when

        //then
    }

    @Test
    @DisplayName("주문시 - 주문시 쿠폰 사용 여부 파악을 통한 가격 할인 제공")
    void itemRepo_3() throws Exception{
        //given

        //when

        //then
    }



}