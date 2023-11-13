package com.zowiac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "zowiac_map")
public class UserEntity {
    @Id
    @Column(name = "user_name")
    private String username;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Column(name = "user_pass")
    private String userPass;
    @Column(name = "pin")
    private String pin;
    @Column(name = "pin_validity")
    private Date pinValidity;
    @Column(name = "hunter")
    private String hunter;
    @Column(name = "skip_tutorial")
    private String skipTutorial;
    @Column(name = "skip_tutorial_report")
    private String skipTutorialReport;

    @Column(name = "language")
    private String language;

    @JsonIgnore
    private transient List<UserRolesEntity> userRoles;

    private transient String userRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getNameCombined() {
        String nameCombined = "";
        if (firstname != null)
            nameCombined = firstname + " ";
        if (lastname != null)
            nameCombined += lastname;
        return nameCombined;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    //TODOO: Passwort nicht JSON übergeben
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getPinValidity() {
        return pinValidity;
    }

    public void setPinValidity(Date pinValidity) {
        this.pinValidity = pinValidity;
    }

    public void setPinData() {
        setPin(UUID.randomUUID().toString());

        java.util.Date now = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        setPinValidity(new Date(calendar.getTime().getTime()));
    }


    public List<UserRolesEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRolesEntity> userRoles) {
        this.userRoles = userRoles;
    }

    public String getUserRolesNames() {
        StringBuilder ret = new StringBuilder();
        if (userRoles != null)
            for (UserRolesEntity userRole : userRoles) {
                if (ret.length() > 0) {
                    ret.append(", ");
                }
                ret.append(userRole.getUserRole());
            }
        return ret.toString();

    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getHunter() {
        return hunter;
    }

    public void setHunter(String hunter) {
        this.hunter = hunter;
    }

    public String getSkipTutorial() {
        return skipTutorial;
    }

    public void setSkipTutorial(String skipTutorial) {
        this.skipTutorial = skipTutorial;
    }

    public String getSkipTutorialReport() {
        return skipTutorialReport;
    }

    public void setSkipTutorialReport(String skipTutorialReport) {
        this.skipTutorialReport = skipTutorialReport;
    }


    public boolean isHunter() {
        return (hunter != null && hunter.equals("J"));
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLanguageName() {
        if (language != null && language.equals("en"))
            return "English";
        else
            return "Deutsch";

    }


}
