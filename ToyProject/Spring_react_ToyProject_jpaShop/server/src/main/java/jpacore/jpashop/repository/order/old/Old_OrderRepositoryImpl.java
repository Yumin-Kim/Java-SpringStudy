package jpacore.jpashop.repository.order.old;

import jpacore.jpashop.domain.Order;
import jpacore.jpashop.repository.order.dto.UpdateOrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class Old_OrderRepositoryImpl implements Old_OrderRepository {

    private final EntityManager em;

    @Override
    public void save(Order order) {
        em.persist(order);
    }

    @Override
    public Optional<Order> findOneOrder(Long id) {
        Order order = em.find(Order.class, id);
        return Optional.ofNullable(order);
    }

    @Override
    public Optional<List<Order>> findAllOrders() {
        List resultList = em.createQuery("select o from Order o " +
                " join o.member m" +
                " join o.delivery d " +
                " join o.orderItems oi ",Order.class)
                .getResultList();
        return Optional.ofNullable(resultList);
    }

    @Override
    public Optional<List<Order>> findAllOrdersPaging(int offset, int limit) {
//        em.createQuery()
        return Optional.empty();
    }

    @Override
    public Optional<Order> updateOrder(Long orderId, UpdateOrderInfo updateOrderInfo) {

        return Optional.empty();
    }

    @Override
    public Optional<Order> cancelOrder(Long id) {
        return Optional.empty();
    }
}
