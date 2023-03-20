package com.zowiac.service;

import com.zowiac.commons.EmailText;
import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderLogEntity;
import com.zowiac.print.ReceiptPdf;
import com.zowiac.respository.OrderRespository;
import org.springframework.stereotype.Component;

import java.util.Date;
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
    public void cancelOrder(Long orderId, String userName) {
        OrderEntity order = getOrderRespository().findById(orderId).get();
        if (!order.isCanceled()) {
            order.setCanceled(true);
            order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde storniert"));
            getOrderRespository().save(order);
        }
    }

    // create receipt
    public void createReceipt(Long orderId, String userName) {
        try {
            OrderEntity order = getOrderRespository().findById(orderId).get();
            if (!order.isCanceled() && order.getReceiptId() == null) {
                Long receitId = getOrderRespository().findMaxReceiptId();
                if (receitId == null)
                    receitId = 0l;
                receitId++;
                order.setReceiptId(receitId);
                order.setReceiptDate(new Date());
                order.setReceiptCreated(true);
                order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde erstellt"));
                getOrderRespository().save(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //send receipt to customer
    public void sendReceipt(Long orderId, String userName) {
        try {
            OrderEntity order = getOrderRespository().findById(orderId).get();
            if (!order.isCanceled() && !order.isReceiptSent()) {
                ReceiptPdf receiptPdf = new ReceiptPdf(order);
                byte[] pdfAsBytes = receiptPdf.createPdf();

                getEmailService().sendMail(order.getEmail(), "Ihre Rechnung - Rechnungsnummer " + order.getReceiptId() , EmailText.createTextRechnung(order), pdfAsBytes);

                order.setReceiptSent(true);

                order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde versendet"));
                getOrderRespository().saveAndFlush(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //send parcitipantion confirmation to customer
    public void sendParticipationConfirmation(OrderEntity order) throws Exception {
        getEmailService().sendMail(order.getEmail(), "Bestätigung Ihrer Registrierung für die ZOWIAC-Konferenz", EmailText.createTeilnahme(order));
    }


    //Settle receipt by admin
    public void bookReceipt(Long orderId, String userName) {
        try {
            OrderEntity order = getOrderRespository().findById(orderId).get();
            sendParticipationConfirmation(order);
            order.setSettled(true);
            order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde verbucht"));
            getOrderRespository().save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
