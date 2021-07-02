package jpacore.jpashop.controller;

import jpacore.jpashop.domain.Item;
import jpacore.jpashop.domain.Member;
import jpacore.jpashop.domain.Order;
import jpacore.jpashop.repository.OrderSearch;
import jpacore.jpashop.service.ItemSerrvice;
import jpacore.jpashop.service.MemberService;
import jpacore.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemSerrvice itemSerrvice;

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemSerrvice.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
        return "order/orderform";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count
    ) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancel(@PathVariable("orderId") Long orderId) {
        System.out.println(orderId);
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
