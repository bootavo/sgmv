package com.app.sgmv.sgmv.entities.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gtufinof on 6/17/18.
 */

public class UsersResults {

    @SerializedName("users")
    @Expose
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UsersResults{" +
                "users=" + users +
                '}';
    }
}
