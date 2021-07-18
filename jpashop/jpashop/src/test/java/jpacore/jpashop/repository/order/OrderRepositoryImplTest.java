package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Delivery;
import jpacore.jpashop.domain.DeliveryStatus;
import jpacore.jpashop.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("주문 정보 저장")
    public void save_test() throws Exception{
        //given
        Delivery createDelivery = Delivery.builder()
                .address(Address.createAddress("서울", "서울길1", "123123"))
                .status(DeliveryStatus.StART)
                .build();
        Order order = Order.createOrder(createDelivery, LocalDateTime.now());
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