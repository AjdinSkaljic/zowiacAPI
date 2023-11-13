package com.zowiac.service;

import com.zowiac.commons.BusinessException;
import com.zowiac.commons.EmailText;
import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderLogEntity;
import com.zowiac.model.OrderPositionEntity;
import com.zowiac.print.ReceiptPdf;
import com.zowiac.respository.OrderRespository;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class OrderService {
    private Logger logger = Logger.getLogger(OrderService.class.getName());

    private final OrderRespository orderRespository;
    private final EmailService emailService;

    public OrderService(OrderRespository orderRespository, EmailService emailService) {
        this.orderRespository = orderRespository;
        this.emailService = emailService;
    }

    public OrderEntity createOrder(OrderEntity order) {
        if (order.getOrderPositions() != null) {
            order.getOrderPositions().forEach(position -> {
                position.setOrder(order);
                position.initPrice();
            });
        }

        if (order.getOrderLogs() == null)
            order.setOrderLogs(new HashSet<>());
        order.getOrderLogs().add(new OrderLogEntity(order, "system", "Bestellung wurde erstellt"));

        order.setOrderDate(new Date());
        getOrderRespository().saveAndFlush(order);

        sendOrderConfirmation(order);
        return order;
    }

    public void sendOrderConfirmation(OrderEntity order) {
        try {
            getEmailService().sendMail(order.getEmail(), "Bestellung bei Zowiac", EmailText.createBestellbestaetigung(order));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fehler beim versenden der Bestellbestätigung", e);
        }

        //TODO: Eine Weitere Email an info@zowiac.eu

    }


    public void sendParticipationFollowup(Long[] ids, String username) throws Exception {
        for (Long id : ids) {
            OrderEntity order = getOrderRespository().findById(id).get();
            for (OrderPositionEntity visitor : order.getVisitors()) {
                if (!visitor.isFollowup()) {
                    if (visitor.getEmail() != null && !visitor.getEmail().isEmpty()) {
                        getEmailService().sendMail(visitor.getEmail(), EmailText.KONF_NAME + ": Zugang zu den Vorträgen und weiteren Materialien", EmailText.createTeilnahmeFollowup(visitor));
                        visitor.setFollowup(true);
                        order.getOrderLogs().add(new OrderLogEntity(order, username, "Followup für " + visitor.getEmail() + " wurde versendet"));
                    } else {
                        visitor.setFollowup(true);
                        order.getOrderLogs().add(new OrderLogEntity(order, username, "Followup für " + visitor.getName() + " konnte nicht versendet werden"));
                    }
                }
            }
            getOrderRespository().save(order);
        }
    }


    public void cancelOrder(Long orderId, String userName) {
        OrderEntity order = getOrderRespository().findById(orderId).get();
        if (!order.isCanceled()) {
            order.setCanceled(true);
            order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde storniert"));
            getOrderRespository().save(order);
        }
    }

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
            logger.log(Level.SEVERE, "Fehler beim erstellen der Rechnung", e);
        }
    }

    public void sendReceipt(Long orderId, String userName) throws BusinessException {
        try {
            OrderEntity order = getOrderRespository().findById(orderId).get();
            ReceiptPdf receiptPdf = new ReceiptPdf(order);
            byte[] pdfAsBytes = receiptPdf.createPdf();
            if (!order.isCanceled() && !order.isReceiptSent()) {

                getEmailService().sendMail(order.getEmail(), "Ihre Rechnung - Rechnungsnummer " + order.getReceiptId(),
                        EmailText.createTextRechnung(order), "Rechnung_" + order.getReceiptId().toString(), pdfAsBytes);

                order.setReceiptSent(true);

                order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde versendet"));
                getOrderRespository().saveAndFlush(order);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fehler beim versenden der Rechnung", e);
            throw new BusinessException("Fehler beim versenden der Rechnung" + e.getMessage());
        }
    }

    public void sendParticipationConfirmation(OrderEntity order) throws Exception {
        getEmailService().sendMail(order.getEmail(), "Bestätigung Ihrer Registrierung für die ZOWIAC-Konferenz", EmailText.createTeilnahme(order));
    }

    public void bookReceipt(Long orderId, String userName) {
        try {
            OrderEntity order = getOrderRespository().findById(orderId).get();
            sendParticipationConfirmation(order);
            order.setSettled(true);
            order.getOrderLogs().add(new OrderLogEntity(order, userName, "Rechnung wurde verbucht"));
            getOrderRespository().save(order);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Fehler beim Verbuchen der Rechnung", e);
        }
    }


    //load order by id
    public OrderEntity loadOrderById(Long id) {
        OrderEntity order = getOrderRespository().findById(id).orElse(null);
        if (order != null) {
            order.initCounts();
        }
        return order;
    }

    //find all orders
    public List<OrderEntity> findAll() {
        List<OrderEntity> orderList = getOrderRespository().findAll();
        if (orderList != null)
            orderList.forEach(order -> order.initCounts());
        return orderList;
    }

    public OrderRespository getOrderRespository() {
        return orderRespository;
    }

    public EmailService getEmailService() {
        return emailService;
    }
}
