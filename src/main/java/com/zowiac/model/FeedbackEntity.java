package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;


@Entity
@Table(name = "feedback", schema = "zowiac_map")
public class FeedbackEntity {
    public final static String STATUS_NEW = "N";
    public final static String STATUS_DONE = "D";

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "status")
    private String status;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
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
