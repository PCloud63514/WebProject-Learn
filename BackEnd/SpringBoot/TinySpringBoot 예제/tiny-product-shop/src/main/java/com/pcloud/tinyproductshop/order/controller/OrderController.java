package com.pcloud.tinyproductshop.order.controller;

import com.pcloud.tinyproductshop.member.service.MemberService;
import com.pcloud.tinyproductshop.order.domain.OrderSearch;
import com.pcloud.tinyproductshop.order.service.OrderService;
import com.pcloud.tinyproductshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequestMapping("order")
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ProductService productService;

    @GetMapping
    public String createOrderForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("products", productService.findProducts());
        return "order/orderForm";
    }

    @PostMapping
    public String createOrderForm(@RequestParam("memberId") Long memberId, @RequestParam("productId") Long productId
        ,@RequestParam("count") int count) {
        orderService.order(memberId, productId, count);
        return "redirect:/order/list";
    }

    @GetMapping("list")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", orderService.searchOrders(orderSearch));
        return "order/orderList";
    }

    @PostMapping("{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancel(orderId);
        return "redirect:/order/list";
    }
}
