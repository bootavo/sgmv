package com.app.sgmv.sgmv.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("results")
    @Expose
    private LoginResults results;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LoginResults getResults() {
        return results;
    }

    public void setResults(LoginResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }
}
