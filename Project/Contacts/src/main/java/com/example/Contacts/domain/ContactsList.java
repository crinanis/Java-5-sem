package com.example.Contacts.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactsList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer contactID;
    private String contactName;
    private String contactNumber;

    public ContactsList(){

    }
    public ContactsList(String contactName, String contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
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
}
