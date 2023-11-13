package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "order_positions", schema = "zowiac_map")
public class OrderPositionEntity {
    public final static String TYPE_VISITOR = "V";
    public final static String TYPE_POSTER = "P";
    public final static String TYPE_SPEECH = "S";


    public final static String DISCOUNT_TYPE_NORMAL = "N";
    public final static String DISCOUNT_TYPE_STUDENT = "S";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "sex")
    private String sex;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "firstname")
    private String firstname;

    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "abstract")
    private String abstractNote;

    @Basic
    @Column(name = "note")
    private String note;

    @Basic
    @Column(name = "topic")
    private String topic;

    @Basic
    @Column(name = "discountType")
    private String discountType;

    @Basic
    @Column(name = "price")
    private Double price;

    @Basic
    @Column(name = "followup")
    private boolean followup;


    public OrderPositionEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getAbstractNote() {
        return abstractNote;
    }

    public void setAbstractNote(String abstractNote) {
        this.abstractNote = abstractNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isFollowup() {
        return followup;
    }

    public void setFollowup(boolean followup) {
        this.followup = followup;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getFullname() {
        String fullname = "";

        if (sex != null) {
            if (sex.equals("f"))
                fullname = "Frau ";
            else if (sex.equals("m"))
                fullname = "Herr ";
        }

        if (this.title != null) {
            if (title.equals("dr"))
                fullname += "Dr. ";
            else if (title.equals("prof"))
                fullname += "Prof. Dr. ";
        }

        fullname += this.firstname + " " + this.lastname;

        return fullname;
    }


    public String getAnredeMail() {
        if (sex == null) {
            return "Sehr geehrte Damen und Herren,";
        }

        String anrede = "";
        if (sex.equals("f"))
            anrede = "Sehr geehrte Frau ";
        else if (sex.equals("m"))
            anrede = "Sehr geehrter Herr ";
        else
            return "Sehr geehrte Damen und Herren,";

        if (this.title != null) {
            if (title.equals("dr"))
                anrede += "Dr. ";
            else if (title.equals("prof"))
                anrede += "Prof. Dr. ";
        }

        return anrede + this.lastname + ",";
    }


    public String getDiscountTypeFormatted() {
        if (discountType != null && discountType.equals(DISCOUNT_TYPE_STUDENT))
            return "Student";
        else
            return "Normal";
    }


    public String getTopicFormatted() {
        if (topic != null) {
            switch (topic) {
                case "1":
                    return "1. Thema";
                case "2":
                    return "2. Thema";
                case "3":
                    return "3. Thema";
                case "4":
                    return "4. Thema";
                case "5":
                    return "5. Thema";
            }
        }
        return "unbekanntes Thema";
    }

    public void initPrice() {
        if (this.type.equals(TYPE_VISITOR)) {
            if (discountType != null && discountType.equals(DISCOUNT_TYPE_STUDENT))
                this.price = 30.0;
            else
                this.price = 60.0;
        } else {
            this.price = 0.0;
        }

    }

}
