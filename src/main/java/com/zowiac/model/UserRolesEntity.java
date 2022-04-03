package com.zowiac.model;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", schema = "zowiac_map")
@IdClass(UserRolesEntityPK.class)
public class UserRolesEntity {
    public final static String ROLE_ADMIN = "Admin";
    public final static String ROLE_USER = "User";
    public final static String ROLE_LOCKED = "Locked";
    @Id
    @Column(name = "user_name")
    private String userName;
    @Id
    @Column(name = "user_role")
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

    public boolean isLocked() {
        return (userRole != null && userRole.equals(ROLE_LOCKED));
    }

}
