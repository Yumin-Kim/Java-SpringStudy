package jpacore.jpashop.service;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.domain.items.Book;
import jpacore.jpashop.exception.NotEnoughStockException;
import jpacore.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
@Autowired
OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Test
    @DisplayName("상품 주문")
    public void orderTest() throws Exception{
        //given
        Member member = getMember("Hello");

        Item book = getItem("Hello world", 10000, 10);

        int orderCount = 2;
        //when
        Long order = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(order);
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER,getOrder.getOrderStatus());
        assertEquals("주문한 상품 종류 수가 정확", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 * 수량이다.",10000*orderCount,getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야한다.",8,book.getStackQuantity());
    }

    private Item getItem(String name, int price, int stock) {
        Item book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStackQuantity(stock);
        em.persist(book);
        return book;
    }

    private Member getMember(String name) {
        Member member = new Member();
        member.setName( name);
        member.setAddress(new Address("city", "street", "123-123"));
        em.persist(member);
        return member;
    }

    @Test()
    @DisplayName("상품 주문 초과")
    public void exceedOrder() throws Exception{
        //given
        Member member = getMember("Hello");
        Item item = getItem("Hello world", 10000, 10);
        int orderCount = 11;
        //when
        try{
            orderService.order(member.getId(), item.getId(), orderCount);
        }catch (NotEnoughStockException e){
            return;
        }
        fail("재고 수량 초과");
        //then
    }

    @Test
    @DisplayName("주문 취소")
    @Transactional
    public void cancelTest() throws Exception{
        //given(준비 단계)
        Member hello = getMember("Hello");
        Item hello_world = getItem("Hello world", 10000, 10);

        int orderCount = 2;

        Long orderid = orderService.order(hello.getId(), hello_world.getId(), orderCount);
        orderService.order(hello.getId(), hello_world.getId(), orderCount);
        //when(실제 테스트하기위한 로직작성)

        orderService.cancelOrder(orderid);
        //then
        Order getOrder = orderRepository.findOne(orderid);
        System.out.println(OrderStatus.CANCEL);
        em.flush();
        assertEquals("주문 취소 상태 확인", OrderStatus.CANCEL, getOrder.getOrderStatus());
//        assertEquals("재고 복구 확인",10,hello_world.getStackQuantity());
    }


}
