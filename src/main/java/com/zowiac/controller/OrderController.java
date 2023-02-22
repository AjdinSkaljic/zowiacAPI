package com.zowiac.controller;

import com.zowiac.model.OrderEntity;
import com.zowiac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders/{id}")
    @ResponseBody
    public OrderEntity findById(@PathVariable("id") Long id) {
        return getOrderService().loadOrderById(id);
    }


    @GetMapping("/orders")
    @ResponseBody
    public List<OrderEntity> finaAll() {
        return getOrderService().findAll();
    }


    @PostMapping("/orders/{id}")
    @ResponseBody
    public OrderEntity create(@RequestBody OrderEntity order) {
        return getOrderService().createOrder(order);
    }


    public OrderService getOrderService() {
        return orderService;
    }
}
