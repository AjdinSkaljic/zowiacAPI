package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "order_date")
    private Date orderDate;

    private transient int countVisitors;

    private transient int countPosters;

    private transient int countSpeeches;
    @Basic
    @Column(name = "receipt_id")
    private Long receiptId;

    @Basic
    @Column(name = "receipt_date")
    private Date receiptDate;
    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Basic
    @Column(name = "lastname")
    private String lastname;

    @Basic
    @Column(name = "company")
    private String company;

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

    public String getIdFormated() {
        return String.format("%04d", getId());
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

    public String getReceiptWithProjekt() {
        return "64150113/" + String.format("%04d", getReceiptId());
    }

    public void setReceiptId(Long receiptId) {
        this.receiptId = receiptId;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
            return orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_POSTER)).collect(Collectors.toList());
        return null;
    }

    public List<OrderPositionEntity> getVisitors() {
        if (orderPositions != null)
            return orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_VISITOR)).collect(Collectors.toList());
        return null;
    }


    public List<OrderPositionEntity> getSpeeches() {
        if (orderPositions != null)
            return orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_SPEECH)).collect(Collectors.toList());
        return null;
    }


    public boolean isReceiptCreated() {
        return receiptCreated;
    }

    public void setReceiptCreated(boolean receiptCreated) {
        this.receiptCreated = receiptCreated;
    }

    @JsonFormat(pattern = "dd.MM.yyyy")
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


    public String getOrderDateFormatted() {
        if (orderDate != null)
            return new java.text.SimpleDateFormat("dd.MM.yyyy").format(orderDate);
        return "";
    }

    @JsonFormat(pattern = "dd.MM.yyyy")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setVisitors(List<OrderPositionEntity> visitors) {
        if (orderPositions == null)
            orderPositions = new HashSet<>(visitors);
        else
            orderPositions.addAll(visitors);
    }

    public void setInputs(List<OrderPositionEntity> posters) {
        if (orderPositions == null)
            orderPositions = new HashSet<>(posters);
        else
            orderPositions.addAll(posters);
    }

    public int getCountSpeeches() {
        return countSpeeches;
    }

    public void setCountSpeeches(int countSpeeches) {
        this.countSpeeches = countSpeeches;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

    public double getReceiptSum() {
        try {
            if (orderPositions != null)
                return orderPositions.stream().mapToDouble(OrderPositionEntity::getPrice).sum();
        } catch (Exception ignore) {

        }
        return 0;
    }


    public boolean isFollowup() {
        try {
            for (OrderPositionEntity visitor : getVisitors())
                if (!visitor.isFollowup())
                    return false;
        } catch (Exception ignore) {
            return false;

        }
        return true;
    }


    public void initCounts() {
        if (orderPositions != null) {
            countVisitors = (int) orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_VISITOR)).count();
            countPosters = (int) orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_POSTER)).count();
            countSpeeches = (int) orderPositions.stream().filter(orderPositionEntity -> orderPositionEntity.getType().equals(OrderPositionEntity.TYPE_SPEECH)).count();
        }
    }


}
