package com.example.Contacts.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
public class ContactsList implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long contactID;
    @Length(max = 55, message = "Name is too long")
    private String contactName;
    @NotBlank(message = "Please fill contact number")
    @Length(max = 13, message = "Number is too long")
    private String contactNumber;
    @ManyToOne(fetch = FetchType.EAGER) //одному пользователю соответствует много контактов
    @JoinColumn(name = "userID")
    private ContactsUsers contactOwner;

    private String filename;

    public ContactsList(){

    }
    public ContactsList(String contactName, String contactNumber, ContactsUsers userLogin) {
        this.contactOwner = userLogin;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public String getOwnerName() {
        return contactOwner != null ? contactOwner.getCuserLogin() : "<none>";
    }
    public void setContactOwner(ContactsUsers userLogin){
        this.contactOwner = userLogin;
    }
    public ContactsUsers getContactOwner() {
        return contactOwner;
    }
    public void setContactID(Long contactID) {
        this.contactID = contactID;
    }
    public Long getContactID() {
        return contactID;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    public String getContactName() {
        return contactName;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFilename() {
        return filename;
    }
}
