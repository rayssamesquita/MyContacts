package com.example.mycontacts.entity;

import com.google.gson.annotations.SerializedName;

public class Contact {

    private int id;

    private String name;

    private String company;

    private String photo;

    @SerializedName("new")
    private boolean newContact;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setNewContact(boolean newContact) {
        this.newContact = newContact;
    }

    public boolean isNewContact() {
        return newContact;
    }
}
