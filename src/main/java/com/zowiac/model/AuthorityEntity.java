package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "authorities", schema = "zowiac_map")
public class AuthorityEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "phone_permitted")
    private String phonePermitted;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "web")
    private String web;


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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhonePermitted() {
        return phonePermitted;
    }

    public void setPhonePermitted(String phonePermitted) {
        this.phonePermitted = phonePermitted;
    }

    public boolean isPhonePermitted() {
        return (phonePermitted != null && phonePermitted.equals("J"));
    }


}
