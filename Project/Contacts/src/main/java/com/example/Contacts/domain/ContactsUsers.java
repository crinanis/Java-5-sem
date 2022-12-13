package com.example.Contacts.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "contacts_users")
public class ContactsUsers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userID;
    @NotBlank(message = "Username cannot be empty")
    private String cuserLogin;
    @NotBlank(message = "Password cannot be empty")
    private String cuserPassword;
    private String cuserName;
    private boolean active;
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private String activationCode;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "contacts_roles", joinColumns = @JoinColumn(name = "userID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public boolean isAdmin(){
        return roles.contains(Role.ADMIN);
    }

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getActivationCode() {
        return activationCode;
    }
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}
