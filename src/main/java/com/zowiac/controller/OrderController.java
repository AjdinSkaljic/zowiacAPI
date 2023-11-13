package com.zowiac.controller;

import com.zowiac.model.OrderEntity;
import com.zowiac.model.OrderPositionEntity;
import com.zowiac.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/public/order")
    @ResponseBody
    public OrderEntity createOrder(@RequestBody OrderEntity order) throws Exception {
        return getOrderService().createOrder(order);
    }


    @GetMapping("/orders/export/{art}")
    public void exportCSV(@PathVariable("art") String art, HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<OrderEntity> orders = getOrderService().findAll();

        if (art.equals(OrderPositionEntity.TYPE_VISITOR)) {
            response.getWriter().println("ID;Teilnehmer;Organisation;E-Mail;Type;Besteller;Datum;");
            if (orders != null) {
                orders.forEach(order -> {
                    if (!order.isCanceled()) {
                        for (OrderPositionEntity visitor : order.getVisitors()) {
                            try {
                                StringBuilder line = new StringBuilder();
                                addFieldCSV(line, order.getId().toString());
                                addFieldCSV(line, visitor.getFullname());
                                addFieldCSV(line, order.getCompany());
                                addFieldCSV(line, visitor.getEmail());
                                addFieldCSV(line, visitor.getDiscountTypeFormatted());
                                addFieldCSV(line, order.getFullname());
                                addFieldCSVLast(line, order.getOrderDateFormatted());
                                response.getWriter().println(line);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
        } else if (art.equals(OrderPositionEntity.TYPE_POSTER)) {
            response.getWriter().println("ID;Titel; Thema; Abstract;sonst. Hinweis;Besteller ;Datum;");
            if (orders != null) {
                orders.forEach(order -> {
                    if (!order.isCanceled()) {
                        for (OrderPositionEntity poster : order.getPosters()) {
                            try {
                                StringBuilder line = new StringBuilder();
                                addFieldCSV(line, order.getId().toString());
                                addFieldCSV(line, poster.getName());
                                addFieldCSV(line, poster.getTopicFormatted());
                                addFieldCSV(line, poster.getAbstractNote());
                                addFieldCSV(line, poster.getNote());
                                addFieldCSV(line, order.getFullname());
                                addFieldCSVLast(line, order.getOrderDateFormatted());
                                response.getWriter().println(line);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
        } else if (art.equals(OrderPositionEntity.TYPE_SPEECH)) {
            response.getWriter().println("ID;Titel; Abstract; sonst. Hinweis;Besteller ;Datum;");
            if (orders != null) {
                orders.forEach(order -> {
                    if (!order.isCanceled()) {
                        for (OrderPositionEntity speech : order.getSpeeches()) {
                            try {
                                StringBuilder line = new StringBuilder();
                                addFieldCSV(line, order.getId().toString());
                                addFieldCSV(line, speech.getName());
                                addFieldCSV(line, speech.getAbstractNote());
                                addFieldCSV(line, speech.getNote());
                                addFieldCSV(line, order.getFullname());
                                addFieldCSVLast(line, order.getOrderDateFormatted());
                                response.getWriter().println(line);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
            }
        }
        response.getWriter().close();
    }

    private void addFieldCSV(StringBuilder line, String field) {
        line.append("\"");
        if (field != null)
            line.append(field.replaceAll("\"", "\"\""));
        line.append("\"");
        line.append(";");
    }

    private void addFieldCSVLast(StringBuilder line, String field) {
        addFieldCSV(line, field);
        line.append(";");
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
    public void sendReceipt(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
        getOrderService().sendReceipt(id, request.getRemoteUser());
    }


    @GetMapping("/orders/sendFollowup/{ids}")
    @ResponseBody
    public void sendFollowup(@PathVariable("ids") Long[] ids, HttpServletRequest request) throws Exception {
        getOrderService().sendParticipationFollowup(ids, request.getRemoteUser());
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


    public OrderService getOrderService() {
        return orderService;
    }
}
