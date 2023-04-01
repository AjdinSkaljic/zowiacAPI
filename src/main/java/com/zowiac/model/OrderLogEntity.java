package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order_logs", schema = "zowiac_map")
public class OrderLogEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Basic
    @Column(name = "date_time")
    private Date dateTime;

    @Basic
    @Column(name = "username")
    private String username;

    @Basic
    @Column(name = "message")
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderLogEntity() {
    }

    public OrderLogEntity(OrderEntity order, String username, String message) {
        this.order = order;
        this.username = username;
        this.message = message;
        this.dateTime = new Date();
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @JsonFormat(pattern="dd.MM.yyyy HH:mm")
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "OrderLogEntity{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
