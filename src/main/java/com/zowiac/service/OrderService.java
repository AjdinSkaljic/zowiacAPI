package com.zowiac.service;

import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderPositionEntity;
import com.zowiac.respository.OrderRespository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderService {

    private final OrderRespository orderRespository;
    private final EmailService emailService;

    public OrderService(OrderRespository orderRespository, EmailService emailService) {
        this.orderRespository = orderRespository;
        this.emailService = emailService;
    }

    //create Order
    public OrderEntity createOrder(OrderEntity orderEntity) {
        return getOrderRespository().save(orderEntity);
    }

    //send Mail Order Confirmation to Customer
    public void sendOrderConfirmation(OrderEntity orderEntity) {
        try {
            getEmailService().sendMail(orderEntity.getEmail(), "Bestellung bei Zowiac", "Vielen Dank für Ihre Bestellung bei Zowiac. Ihre Bestellnummer lautet: " + orderEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //cancel order by admin
    public void cancelOrder(OrderEntity orderEntity) {
        orderEntity.setCanceled(true);
        getOrderRespository().save(orderEntity);
    }

    // create receipt
    public void createReceipt(OrderEntity orderEntity) {
        //orderEntity.setReceipt(true);
        getOrderRespository().save(orderEntity);
    }

    //send receipt to customer
    public void sendReceipt(OrderEntity orderEntity) {
        try {
            getEmailService().sendMail(orderEntity.getEmail(), "Rechnung von Zowiac", "Vielen Dank für Ihre Bestellung bei Zowiac. Ihre Rechnung finden Sie im Anhang.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //send parcitipantion confirmation to customer
    public void sendParticipationConfirmation(OrderEntity orderEntity) {
        try {
            getEmailService().sendMail(orderEntity.getEmail(), "Teilnahmebestätigung von Zowiac", "Vielen Dank für Ihre Teilnahme bei Zowiac. Ihre Bestellnummer lautet: " + orderEntity.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Settle receipt by admin
    public void settleReceipt(OrderEntity orderEntity) {
        orderEntity.setSettled(true);
        getOrderRespository().save(orderEntity);
    }

    //get list visitors
    public List<OrderPositionEntity> getListVisitors() {
        //TODO
        return null;
    }

    public List<OrderPositionEntity> getListPosters() {
        //TODO
        return null;
    }

    //load order by id
    public OrderEntity loadOrderById(Long id) {
        return getOrderRespository().findById(id).orElse(null);
    }

    //find all orders
    public List<OrderEntity> findAll() {
        return getOrderRespository().findAll();
    }


    public OrderRespository getOrderRespository() {
        return orderRespository;
    }

    public EmailService getEmailService() {
        return emailService;
    }
}
