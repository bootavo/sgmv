package com.app.sgmv.sgmv.entities.user;

import com.app.sgmv.sgmv.entities.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gtufinof on 6/17/18.
 */

public class UsersResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("results")
    @Expose
    private UsersResults results;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UsersResults getResults() {
        return results;
    }

    public void setResults(UsersResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "UsersResponse{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }
}
