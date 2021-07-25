package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Delivery;
import jpacore.jpashop.domain.enumtype.DeliveryStatus;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.repository.order.old.Old_OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    Old_OrderRepository orderRepository;

    @Test
    @DisplayName("주문 정보 저장")
    public void save_test() throws Exception{
        //given
        Delivery createDelivery = Delivery.builder()
                .address(Address.createAddress("서울", "서울길1", "123123"))
                .status(DeliveryStatus.START)
                .build();
//        Order order = Order.createOrder(createDelivery, item, member);
        //        orderRepository.save();
        //when
        //then
        Assertions.assertNotNull(orderRepository);
    }

//    @Test
//    @DisplayName("")
//    void () throws Exception{
//        //given
//
//        //when
//
//        //then
//    }

}