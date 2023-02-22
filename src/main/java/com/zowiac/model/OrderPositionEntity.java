package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "order_positions", schema = "zowiac_map")
public class OrderPositionEntity {
    public final static String TYPE_VISITOR = "V";
    public final static String TYPE_POSTER = "P";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Basic
    @Column(name = "order_id")
    private Long orderId;
    @Basic
    @Column(name = "type")
    private String type;

    @Basic
    @Column(name = "name")
    private String name;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
