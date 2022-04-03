package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "animals", schema = "zowiac_map")
public class AnimalEntity {


    public final static String REPORT_TYPE_ZOWIAC = "J";
    public final static String REPORT_TYPE_HUNTING = "H";
    public final static String REPORT_TYPE_OBSERVER = "O";


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "scientific_name")
    private String scientificName;
    @Basic
    @Column(name = "category")
    private String category;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "allow_report")
    private String allowReport;
    @Basic
    @Column(name = "report_type")
    private String reportType;
    @Basic
    @Column(name = "first_describer")
    private String firstDescriber;
    @Basic
    @Column(name = "actual_population")
    private String actualPopulation;
    @Basic
    @Column(name = "forecast_population")
    private String forecastPopulation;
    @Basic
    @Column(name = "parent_id")
    private Long parentId;

    private transient AnimalEntity parentAnimal;


    public AnimalEntity getParentAnimal() {
        return parentAnimal;
    }

    public void setParentAnimal(AnimalEntity parentAnimal) {
        this.parentAnimal = parentAnimal;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAllowReport() {
        return allowReport;
    }

    public void setAllowReport(String allowReport) {
        this.allowReport = allowReport;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public boolean isAllowReport() {
        return (this.allowReport != null && this.allowReport.equals("J"));
    }

    public boolean isZowiac() {
        return (this.reportType != null && this.reportType.equals(REPORT_TYPE_ZOWIAC));
    }

    public String getFirstDescriber() {
        return firstDescriber;
    }

    public void setFirstDescriber(String firstDescriber) {
        this.firstDescriber = firstDescriber;
    }

    public String getActualPopulation() {
        return actualPopulation;
    }

    public void setActualPopulation(String actualPopulation) {
        this.actualPopulation = actualPopulation;
    }

    public String getForecastPopulation() {
        return forecastPopulation;
    }

    public void setForecastPopulation(String forecastPopulation) {
        this.forecastPopulation = forecastPopulation;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public String getParentName() {
        if (parentAnimal != null)
            return parentAnimal.getName();
        return "";
    }

    public String getFullname() {
        String name = getName();
        if (parentAnimal != null)
            name = getParentName() + " - " + parentAnimal.getName();
        return name;
    }
}

