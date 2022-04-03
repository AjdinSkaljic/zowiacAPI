package com.zowiac.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "files", schema = "zowiac_map")
public class FilesEntity {
    public final static String ORIGIN_TYPE_REPORT = "R";
    public final static String ORIGIN_TYPE_HIDE = "H";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "origin_id")
    private Long originId;
    @Basic
    @Column(name = "origin_type")
    private String originType;
    @Basic
    @Column(name = "data")
    private byte[] data;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "create_user")
    private String createUser;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "create_time")
    private Time createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long reportId) {
        this.originId = reportId;
    }


    public String getOriginType() {
        return originType;
    }

    public void setOriginType(String originType) {
        this.originType = originType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Time getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Time createTime) {
        this.createTime = createTime;
    }


}
