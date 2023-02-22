package com.zowiac.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders", schema = "zowiac_map")
public class OrderEntity {
    public final static int MAX_COUNT_VISITORS = 5;
    public final static int MAX_COUNT_POSTERS = 5;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "count_visitors")
    private int countVisitors;
    @Basic
    @Column(name = "count_posters")
    private int countPosters;
    @Basic
    @Column(name = "receipt_id")
    private Long receiptId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "street")
    private String street;

    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "zip")
    private String zip;

    private String email;

    private boolean canceled;

    private boolean settled;

    private List<OrderPositionEntity> orderPositions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountVisitors() {
        return countVisitors;
    }

    public void setCountVisitors(int countVisitors) {
        this.countVisitors = countVisitors;
    }

    public int getCountPosters() {
        return countPosters;
    }

    public void setCountPosters(int countPosters) {
        this.countPosters = countPosters;
    }

    public List<OrderPositionEntity> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPositionEntity> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public Long getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isSettled() {
        return settled;
    }

    public void setSettled(boolean settled) {
        this.settled = settled;
    }
}
