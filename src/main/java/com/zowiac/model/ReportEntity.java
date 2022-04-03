package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zowiac.commons.SqlTimeDesrialize;
import com.zowiac.commons.SqlTimeSerialize;
import com.zowiac.commons.TimestampSerialize;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "reports", schema = "zowiac_map")
public class ReportEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "ref_id")
    private UUID refId;
    @Basic
    @Column(name = "animal_id")
    private Long animalId;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    @JsonSerialize(using = SqlTimeSerialize.class)
    @JsonDeserialize(using = SqlTimeDesrialize.class)
    private Time time;
    @Basic
    @Column(name = "user")
    private String user;
    @Basic
    @Column(name = "latitude")
    private BigDecimal latitude;
    @Basic
    @Column(name = "longitude")
    private BigDecimal longitude;
    @Basic
    @Column(name = "evidence_type")
    private Long evidenceType;
    @Basic
    @Column(name = "hunting")
    private String hunting;
    @Basic
    @Column(name = "hunting_hide")
    private Long huntingHide;
    @Basic
    @Column(name = "count")
    private int count;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "country_code")
    private String countryCode;
    @Basic
    @Column(name = "state")
    private String state;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "locality")
    private String locality;
    @Basic
    @Column(name = "street")
    private String street;
    @Basic
    @Column(name = "house_number")
    private String houseNumber;
    @Basic
    @Column(name = "adress_line")
    private String adressLine;
    @Basic
    @Column(name = "picture_type")
    private String pictureType;
    @Basic
    @Column(name = "picture_date_time")
    @JsonSerialize(using = TimestampSerialize.class)
    private Timestamp pictureDateTime;
    @Basic
    @Column(name = "deleted")
    private String deleted;

    private transient AnimalEntity animalEntity;
    private transient EvidenceTypeEntity evidenceTypeEntity;
    private transient String huntingHideName;
    private transient UserEntity userEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getRefId() {
        return refId;
    }

    public void setRefId(UUID refId) {
        this.refId = refId;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    public void setDate(Date date) {
        this.date = date;
    }


    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public Long getEvidenceType() {
        return evidenceType;
    }

    public void setEvidenceType(Long evidenceType) {
        this.evidenceType = evidenceType;
    }

    public String getHunting() {
        return hunting;
    }

    public void setHunting(String hunting) {
        if (hunting != null && hunting.length() > 1)
            if (hunting.equals("true"))
                this.hunting = "J";
            else
                this.hunting = "N";
        else
            this.hunting = hunting;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAdressLine() {
        return adressLine;
    }

    public void setAdressLine(String adressLine) {
        this.adressLine = adressLine;
    }

    public Long getHuntingHide() {
        return huntingHide;
    }

    public void setHuntingHide(Long huntingHide) {
        this.huntingHide = huntingHide;
    }

    @JsonIgnore
    public AnimalEntity getAnimalEntity() {
        return animalEntity;
    }

    @JsonIgnore
    public void setAnimalEntity(AnimalEntity animalEntity) {
        this.animalEntity = animalEntity;
    }

    public String getAnimalName() {
        if (getAnimalEntity() != null)
            return getAnimalEntity().getName();
        return "";
    }

    @JsonIgnore
    public EvidenceTypeEntity getEvidenceTypeEntity() {
        return evidenceTypeEntity;
    }

    @JsonIgnore
    public void setEvidenceTypeEntity(EvidenceTypeEntity evidenceTypeEntity) {
        this.evidenceTypeEntity = evidenceTypeEntity;
    }

    public String getEvidenceTypeName() {
        if (getEvidenceTypeEntity() != null)
            return getEvidenceTypeEntity().getName();
        return "";
    }

    public String getUserName() {
        if (getUserEntity() != null)
            return getUserEntity().getNameCombined();
        return "";
    }

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public Timestamp getPictureDateTime() {
        return pictureDateTime;
    }

    public void setPictureDateTime(Timestamp pictureDateTime) {
        this.pictureDateTime = pictureDateTime;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getHuntingHideName() {
        return huntingHideName;
    }

    public void setHuntingHideName(String huntingHideName) {
        this.huntingHideName = huntingHideName;
    }

    @JsonIgnore
    public UserEntity getUserEntity() {
        return userEntity;
    }

    @JsonIgnore
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
