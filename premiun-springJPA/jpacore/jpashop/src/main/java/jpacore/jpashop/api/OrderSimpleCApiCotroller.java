package jpacore.jpashop.api;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.domain.OrderStatus;
import jpacore.jpashop.dto.SimpleOrderDto;
import jpacore.jpashop.repository.OrderRepository;
import jpacore.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleCApiCotroller {
    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> allOrder = orderRepository.findByCriteria(new OrderSearch());
        return allOrder;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() {
        return orderRepository.findByCriteria(new OrderSearch()).stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream().map(SimpleOrderDto::new).collect(Collectors.toList());
    }


    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderDto> orderV4(){
        return orderRepository.findOrderDtos();
    }


}
