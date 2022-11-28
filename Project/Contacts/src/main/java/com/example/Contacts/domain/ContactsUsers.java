package com.example.Contacts.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "contacts_users")
public class ContactsUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userID;
    private String cuserLogin;
    private String cuserPassword;
    private String cuserName;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "contacts_roles", joinColumns = @JoinColumn(name = "userID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setCuserLogin(String cuserLogin) {
        this.cuserLogin = cuserLogin;
    }

    public void setCuserPassword(String cuserPassword) {
        this.cuserPassword = cuserPassword;
    }

    public void setCuserName(String cuserName) {
        this.cuserName = cuserName;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getCuserLogin() {
        return cuserLogin;
    }

    public String getCuserPassword() {
        return cuserPassword;
    }

    public String getCuserName() {
        return cuserName;
    }

    public boolean isActive() {
        return active;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
