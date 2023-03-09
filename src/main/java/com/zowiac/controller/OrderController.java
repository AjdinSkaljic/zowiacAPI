package com.zowiac.controller;

import com.zowiac.model.OrderEntity;
import com.zowiac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/orders/cancel/{id}")
    @ResponseBody
    public void cancelOrderById(@PathVariable("id") Long id, HttpServletRequest request) {
        getOrderService().cancelOrder(id, request.getRemoteUser());
    }

    @GetMapping("/orders/createReceipt/{id}")
    @ResponseBody
    public void createReceipt(@PathVariable("id") Long id, HttpServletRequest request) {
        getOrderService().createReceipt(id, request.getRemoteUser());
    }

    @GetMapping("/orders/sendReceipt/{id}")
    @ResponseBody
    public void sendReceipt(@PathVariable("id") Long id, HttpServletRequest request) {
        getOrderService().sendReceipt(id, request.getRemoteUser());
    }


    @GetMapping("/orders/bookReceipt/{id}")
    @ResponseBody
    public void bookReceipt(@PathVariable("id") Long id, HttpServletRequest request) {
        getOrderService().bookReceipt(id, request.getRemoteUser());
    }


    @GetMapping("/orders/{id}")
    @ResponseBody
    public OrderEntity findById(@PathVariable("id") Long id) {
        return getOrderService().loadOrderById(id);
    }


    @GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
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
