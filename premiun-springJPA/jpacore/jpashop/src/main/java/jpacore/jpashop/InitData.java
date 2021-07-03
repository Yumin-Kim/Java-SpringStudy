package jpacore.jpashop;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.domain.items.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitService initService;

    @PostConstruct
     public void init() {
        initService.dbInit1();
    }

    @Transactional
    @Component
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "city1", "street1", "city1");
            Member member1 = createMember("userB", "city2", "street2", "city2");

            Book book1 = createBook("JPA1 Book", 10000);
            Book book2 = createBook("JPA2 Book", 20000);
            Book spring1 = createBook("Spring Book", 10000);
            Book spring2 = createBook("Spring1 Book", 20000);
            em.persist(book1);
            em.persist(book2);
            em.persist(spring1);
            em.persist(spring2);

            OrderItem orderItem = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem1 = OrderItem.createOrderItem(book2, 10000, 1);

            OrderItem orderItem2 = OrderItem.createOrderItem(spring1, 10000, 1);
            OrderItem orderItem3 = OrderItem.createOrderItem(spring2, 10000, 1);

            Delivery delivery = new Delivery();
            Delivery delivery1 = new Delivery();
            delivery.setAddress(member.getAddress());
            delivery1.setAddress(member1.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem, orderItem1);
            Order order1 = Order.createOrder(member1, delivery1, orderItem2, orderItem3);
            em.persist(order);
            em.persist(order1);

        }

        private Book createBook(String s, int i) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setStackQuantity(100);
            book1.setPrice(i);
            return book1;
        }

        private Member createMember(String userA, String city1, String street1, String citycode) {
            Member member = new Member();
            member.setName(userA);
            member.setAddress(new Address(city1, street1, citycode));
            em.persist(member);
            return member;
        }

    }
}

