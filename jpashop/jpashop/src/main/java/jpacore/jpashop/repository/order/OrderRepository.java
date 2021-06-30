package jpacore.jpashop.repository.order;

import jpacore.jpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    //
}
