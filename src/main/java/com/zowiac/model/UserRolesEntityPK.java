package com.zowiac.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRolesEntityPK implements Serializable {
    private String userName;
    private String userRole;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
