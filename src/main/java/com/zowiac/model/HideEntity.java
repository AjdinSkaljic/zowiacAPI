package com.zowiac.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zowiac.commons.SqlTimeDesrialize;
import com.zowiac.commons.SqlTimeSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "hides", schema = "zowiac_map")
public class HideEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "type")
    private Long type;
    @Column(name = "ref_id")
    private UUID refId;
    @Basic
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "country_code")
    private String countryCode;
    @Basic
    @Column(name = "user_name")
    private String userName;
    @Basic
    @Column(name = "published")
    private String published;
    @Basic
    @Column(name = "create_date")
    private Date createDate;
    @Basic
    @Column(name = "create_time")
    @JsonSerialize(using = SqlTimeSerialize.class)
    @JsonDeserialize(using = SqlTimeDesrialize.class)
    private Time createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
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

    public UUID getRefId() {
        return refId;
    }

    public void setRefId(UUID refId) {
        this.refId = refId;
    }
}
