package com.example.Contacts.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "contacts_users")
public class ContactsUsers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    private String cuserLogin;
    private String cuserPassword;
    private String cuserName;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "contacts_roles", joinColumns = @JoinColumn(name = "userID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public ContactsUsers(){

    }

    public ContactsUsers(String cuserLogin, String cuserPassword) {
        this.cuserLogin = cuserLogin;
        this.cuserPassword = cuserPassword;
    }

    public void setUserID(Long userID) {
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

    public Long getUserID() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return cuserPassword;
    }

    @Override
    public String getUsername() {
        return cuserLogin;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
