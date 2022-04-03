package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "evidence_types", schema = "zowiac_map")
public class EvidenceTypeEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "hunting")
    private String hunting;
    @Basic
    @Column(name = "preselect")
    private String preselect;
    @Basic
    @Column(name = "display_authorities")
    private String displayAuthorities;

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

    public String getHunting() {
        return hunting;
    }

    public void setHunting(String hunting) {
        this.hunting = hunting;
    }

    public String getPreselect() {
        return preselect;
    }

    public void setPreselect(String preselect) {
        this.preselect = preselect;
    }

    public String getDisplayAuthorities() {
        return displayAuthorities;
    }

    public void setDisplayAuthorities(String displayAuthorities) {
        this.displayAuthorities = displayAuthorities;
    }
}
