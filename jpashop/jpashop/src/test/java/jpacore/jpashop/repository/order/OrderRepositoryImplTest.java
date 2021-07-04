package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    @DisplayName("회원 정보 저장")
    public void save_test() throws Exception{
        //given
        Order order = Order.createOrder();
//        orderRepository.save();
        //when
        //then
        Assertions.assertNotNull(orderRepository);
    }
}