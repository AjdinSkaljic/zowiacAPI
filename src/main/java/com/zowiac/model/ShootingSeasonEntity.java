package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.zowiac.commons.Convertor;
import com.zowiac.commons.SqlDateDesrialize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shooting_seasons", schema = "zowiac_map")
public class ShootingSeasonEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @JsonDeserialize(using = SqlDateDesrialize.class)
    @Column(name = "date_from")
    private Date dateFrom;
    @Basic
    @JsonDeserialize(using = SqlDateDesrialize.class)
    @Column(name = "date_to")
    private Date dateTo;
    @Basic
    @JsonDeserialize(using = SqlDateDesrialize.class)
    @Column(name = "date_from1")
    private Date dateFrom1;
    @Basic
    @JsonDeserialize(using = SqlDateDesrialize.class)
    @Column(name = "date_to1")
    private Date dateTo1;
    @Basic
    @Column(name = "state")
    private String state;


    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private AnimalEntity animal;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }


    @JsonIgnore
    public AnimalEntity getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalEntity animal) {
        this.animal = animal;
    }


    public String getAnimalName() {
        String name = getAnimal().getName();
        if (getAnimal().getParentAnimal() != null)
            name = getAnimal().getParentName() + " - " + name;
        return name;
    }

    public String getDuration() {
        return Convertor.convertDateSort(getDateFrom()) + " - " + Convertor.convertDateSort(getDateTo());
    }

    public Long getAnimalId() {
        return getAnimal().getId();
    }

    public void setAnimalId(Long id) {
        animal = new AnimalEntity();
        animal.setId(id);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateFrom1() {
        return dateFrom1;
    }

    public void setDateFrom1(Date dateFrom1) {
        this.dateFrom1 = dateFrom1;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDateTo1() {
        return dateTo1;
    }

    public void setDateTo1(Date dateTo1) {
        this.dateTo1 = dateTo1;
    }

    public String getStateName() {
        if (state == null)
            return "";
        switch (state) {
            case "BW":
                return "Baden-Württemberg";
            case "BY":
                return "Bayern";
            case "BE":
                return "Berlin";
            case "BB":
                return "Brandenburg";
            case "HB":
                return "Bremen";
            case "HH":
                return "Hamburg";
            case "HE":
                return "Hessen";
            case "MV":
                return "Mecklenburg-Vorpommern";
            case "NI":
                return "Niedersachsen";
            case "NW":
                return "Nordrhein-Westfalen";
            case "RP":
                return "Rheinland-Pfalz";
            case "SL":
                return "Saarland";
            case "SN":
                return "Sachsen";
            case "ST":
                return "Sachsen-Anhalt";
            case "SH":
                return "Schleswig-Holstein";
            case "TH":
                return "Thüringen";
        }

        return "bundesweit";
    }

}
