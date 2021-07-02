package jpacore.jpashop.service;

import jpacore.jpashop.domain.*;
import jpacore.jpashop.repository.ItemRepository;
import jpacore.jpashop.repository.MemberRepository;
import jpacore.jpashop.repository.OrderRepository;
import jpacore.jpashop.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId , Long  itemId , int count){

        Member member = memberRepository.findOnd(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        //order 만 저장했는데 다른 Entity들은 persist를 하지 않는 이유는
        //Order Entity안에 cascade를 통해서 자동으로 주입하게끔 되어 있다
        orderRepository.save(order);
        return order.getId();
    }
    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
//
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findByCriteria(orderSearch);
    }

}
