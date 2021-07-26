package jpacore.jpashop.service;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.dto.OrderForm;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    InitDataMethod initDataMethod;

    @Test
    @DisplayName("데이터 삽입")
    @Order(0)
    void insert_data() throws Exception {
        initDataMethod.createCoupon();
        initDataMethod.createItem();
        initDataMethod.createMember();
    }

    @Test
    @DisplayName("상품 주문")
    void start_1() throws Exception {
        //given
        OrderForm orderForm = new OrderForm(Address.createAddress("d_city1", "d_street", "d_citycode"), 10, 1000);
        orderService.createV1(1L, 8L, orderForm);
        assertThrows(IllegalStateException.class,()->orderService.createV1(20L, 10L, orderForm));
        //when
//        orderService.create()
        //then
    }

}