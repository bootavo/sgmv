package com.app.sgmv.sgmv.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResults {

    @SerializedName("user")
    @Expose
    private User user;

    @SerializedName("modules")
    @Expose
    private List<Modules> modules;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "LoginResults{" +
                "user=" + user +
                ", modules=" + modules +
                '}';
    }
}
