package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "text", schema = "zowiac_map")
public class TextEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "text")

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
