package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    @Column(name = "receipt_date")
    private Date receiptDate;
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

    @Basic
    @Column(name = "email")
    private String email;

    private boolean canceled;

    private boolean settled;

    private boolean receiptSent;

    private boolean receiptCreated;


    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderLogEntity> orderLogs;

    @JsonIgnore
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderPositionEntity> orderPositions;


    public OrderEntity() {
    }

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

    public Set<OrderLogEntity> getOrderLogs() {
        return orderLogs;
    }

    public void setOrderLogs(Set<OrderLogEntity> orderLogs) {
        this.orderLogs = orderLogs;
    }

    public Set<OrderPositionEntity> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPositionEntity> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public boolean isReceiptSent() {
        return receiptSent;
    }

    public void setReceiptSent(boolean receiptSent) {
        this.receiptSent = receiptSent;
    }


    public List<OrderPositionEntity> getPosters() {
        if (orderPositions != null)
            return orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals("P")).collect(Collectors.toList());
        return null;
    }

    public List<OrderPositionEntity> getVisitors() {
        if (orderPositions != null)
            return orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals("V")).collect(Collectors.toList());
        return null;
    }

    public boolean isReceiptCreated() {
        return receiptCreated;
    }

    public void setReceiptCreated(boolean receiptCreated) {
        this.receiptCreated = receiptCreated;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    //get Receipt date formatted
    public String getReceiptDateFormatted() {
        if (receiptDate != null)
            return new java.text.SimpleDateFormat("dd.MM.yyyy").format(receiptDate);
        return "";
    }

    public void setVisitors(List<OrderPositionEntity> visitors) {
        if (orderPositions == null)
            orderPositions = new HashSet<>(visitors);
        else
            orderPositions.addAll(visitors);
    }

    public void setPosters(List<OrderPositionEntity> posters) {
        if (orderPositions == null)
            orderPositions = new HashSet<>(posters);
        else
            orderPositions.addAll(posters);
    }


    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", countVisitors=" + countVisitors +
                ", countPosters=" + countPosters +
                ", receiptId=" + receiptId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", email='" + email + '\'' +
                ", canceled=" + canceled +
                ", settled=" + settled +
                ", receiptSent=" + receiptSent +
                '}';
    }
}
