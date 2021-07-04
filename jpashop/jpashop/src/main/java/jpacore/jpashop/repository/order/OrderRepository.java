package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Order;
import jpacore.jpashop.repository.order.dto.UpdateOrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//주문자 중심의 로직 작성
public interface OrderRepository {
    // 주문 정보 저장
    void save(Order order);

    // 주문 정보 1조회
    Optional<Order> findOneOrder(Long id);

    // 주문 정보 모두 조회
    Optional<List<Order>> findAllOrders();

    // 주문 정보 페이징 추가하여 조회
    Optional<List<Order>> findAllOrdersPaging(int offset, int limit);

    // 주문 단일 정보 수정
    Optional<Order> updateOrder(Long orderId, UpdateOrderInfo updateOrderInfo);

    // 배달 취소 기능
    Optional<Order> cancelOrder(Long id);

    // 배송 현황에 따른 조회 기능

}
