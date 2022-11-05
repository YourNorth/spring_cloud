package dev.yournorth.order.contorollers;

import dev.yournorth.order.entities.Order;
import dev.yournorth.order.services.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public String postOrder(@RequestBody Order order){
        return orderService.makeOrder(order);
    }
}
