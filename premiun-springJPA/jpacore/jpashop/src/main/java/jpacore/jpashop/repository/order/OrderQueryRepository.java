package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final EntityManager em;

    public List<OrderQueryDto> findOrderQuertDtos() {
        List<OrderQueryDto> orders = findOrders();
        orders.forEach(o -> {
                    List<OrderItemQueryDto> orderItems = findOrderItems(o.getOrderId());
                    o.setOrderItems(orderItems);
                }
        );
        return orders;
    }

    public List<OrderQueryDto> findAllByDto_optimization() {
        List<OrderQueryDto> result = findOrders();
        List<Long> orderIds = result.stream()
                .map(OrderQueryDto::getOrderId)
                .collect(Collectors.toList());

        List<OrderItemQueryDto> orderItems = em.createQuery(
                "select new jpacore.jpashop.repository.order.OrderItemQueryDto(i.name , oi.orderPrice , oi.count , oi.order.id )" +
                        " from OrderItem oi " +
                        " join oi.item i " +
                        "where oi.order.id in :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderIds)
                .getResultList();

        Map<Long, List<OrderItemQueryDto>> orderItemMap = orderItems.stream()
                .collect(Collectors.groupingBy(OrderItemQueryDto::getOrderId));

        result.stream()
                .forEach(orderQueryDto -> orderQueryDto.setOrderItems(orderItemMap.get(orderQueryDto.getOrderId())));

        return result;
    }

    public List<OrderFlatDto> findAllByDto_flat() {
        return em.createQuery(
                "select new jpacore.jpashop.repository.order.OrderFlatDto(o.id , m.name , o.orderDate , o.orderStatus, d.address , i.name , oi.orderPrice , oi.count) " +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d" +
                        " join o.orderItems oi" +
                        " join oi.item i ", OrderFlatDto.class).getResultList();
    }


    private List<OrderItemQueryDto> findOrderItems(Long orderId) {
        return em.createQuery(
                "select new jpacore.jpashop.repository.order.OrderItemQueryDto(i.name , oi.orderPrice , oi.count , oi.order.id )" +
                        " from OrderItem oi " +
                        " join oi.item i " +
                        "where oi.order.id = :orderId", OrderItemQueryDto.class)
                .setParameter("orderId", orderId)
                .getResultList();
    }

    private List<OrderQueryDto> findOrders() {
        return em.createQuery(
                "select new jpacore.jpashop.repository.order.OrderQueryDto(o.id , m.name , o.orderDate,o.orderStatus , d.address )" +
                        " from Order o " +
                        "join o.member m " +
                        "join o.delivery d ", OrderQueryDto.class).getResultList();
    }


}
