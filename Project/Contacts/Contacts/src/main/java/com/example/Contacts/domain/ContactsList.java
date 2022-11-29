package com.example.Contacts.domain;

import javax.persistence.*;

@Entity
public class ContactsList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer contactID;
    private String contactName;
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
    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }
    public Integer getContactID() {
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
