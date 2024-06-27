package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zowiac.commons.SqlDateDesrialize;
import com.zowiac.commons.SqlTimeDesrialize;
import com.zowiac.commons.SqlTimeSerialize;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

public class AddressPointData {
    private Long id;
    private Long animalId;
    @JsonDeserialize(using = SqlDateDesrialize.class)
    private Date date;
    @JsonSerialize(using = SqlTimeSerialize.class)
    @JsonDeserialize(using = SqlTimeDesrialize.class)
    private Time time;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Long animalId) {
        this.animalId = animalId;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
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
}
