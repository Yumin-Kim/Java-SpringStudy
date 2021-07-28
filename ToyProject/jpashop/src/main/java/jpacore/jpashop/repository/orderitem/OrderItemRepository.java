package jpacore.jpashop.repository.orderitem;

import jpacore.jpashop.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

// 주문 상품에 대한 로직 작성
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    // item 재고 수정 >> 관리자가 원하는 상품 재고 값을 받아와 변경
}
