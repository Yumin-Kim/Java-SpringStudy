package jpacore.jpashop.api;

import jpacore.jpashop.domain.Address;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.domain.OrderItem;
import jpacore.jpashop.domain.OrderStatus;
import jpacore.jpashop.repository.OrderRepository;
import jpacore.jpashop.repository.OrderSearch;
import jpacore.jpashop.repository.order.OrderFlatDto;
import jpacore.jpashop.repository.order.OrderItemQueryDto;
import jpacore.jpashop.repository.order.OrderQueryDto;
import jpacore.jpashop.repository.order.OrderQueryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> orderV1() {
        List<Order> findAllOrder = orderRepository.findByCriteria(new OrderSearch());
        findAllOrder.stream().forEach((order) -> {
            order.getMember().getName();
            order.getDelivery().getAddress();
            List<OrderItem> orderItems = order.getOrderItems();
            orderItems.stream().forEach((orderItem) -> orderItem.getItem().getName());
        });
        return findAllOrder;
    }


    @GetMapping("/api/v2/orders")
    public List<OrderDto> orderV2() {
        List<Order> byCriteria = orderRepository.findByCriteria(new OrderSearch());
        return byCriteria.stream()
                .map(OrderDto::new)
                .collect(toList());
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> orderV3() {
        List<Order> findAllItems = orderRepository.findAllWithItem();
        return findAllItems.stream()
                .map(OrderDto::new)
                .collect(toList());

    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> orderV3_1(
            @RequestParam(value = "limit", defaultValue = "100") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Order> allOrderDtos = orderRepository.findAllOrderDtos(limit, offset);
        List<OrderDto> collect = allOrderDtos.stream()
                .map(OrderDto::new)
                .collect(toList());
        return collect;
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> orderV4() {
        return orderQueryRepository.findOrderQuertDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> orderV5() {
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderQueryDto> orderV6() {
        List<OrderFlatDto> flats = orderQueryRepository.findAllByDto_flat();
        System.out.println("flats.size() = " + flats.size());
        Map<OrderQueryDto, List<OrderItemQueryDto>> collect = flats.stream()
                .collect(groupingBy(o -> new OrderQueryDto(o.getOrderId(), o.getName(), o.getOrderDate(), o.getOrderStatus(), o.getAddress()),
                        mapping(o -> new OrderItemQueryDto(o.getName(), o.getOrderPrice(), o.getCount(), o.getOrderId()), toList())
                ));
        System.out.println("collect.size() = " + collect.size());
        return collect.entrySet().stream()
                .map(e -> new OrderQueryDto(e.getKey().getOrderId(),
                        e.getKey().getName(), e.getKey().getOrderDate(), e.getKey().getOrderStatus(),
                        e.getKey().getAddress(), e.getValue()))
                .collect(toList());
    }


    @Getter
    private class OrderDto {
        private String name;
        private OrderStatus orderStatus;
        private Address address;
        private LocalDateTime orderDate;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            this.name = order.getMember().getName();
            this.orderStatus = order.getOrderStatus();
            this.address = order.getDelivery().getAddress();
            this.orderDate = order.getOrderDate();
            this.orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }

    }

    @Getter
    class OrderItemDto {

        private String itemName;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.itemName = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
