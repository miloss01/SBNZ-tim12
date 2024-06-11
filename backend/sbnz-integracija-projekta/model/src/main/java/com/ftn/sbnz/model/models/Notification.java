package com.ftn.sbnz.model.models;

import java.io.Serializable;

public class Notification implements Serializable {

    private String name;
    private String description;
    private AppUser user;

    public Notification(String name, String description, AppUser user) {
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Notification() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
