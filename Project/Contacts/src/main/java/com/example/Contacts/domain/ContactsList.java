package com.example.Contacts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ContactsList {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer contactID;
    private String contactName;
    private String contactNumber;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
