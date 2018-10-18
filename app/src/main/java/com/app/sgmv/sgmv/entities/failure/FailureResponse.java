package com.app.sgmv.sgmv.entities.failure;

import com.app.sgmv.sgmv.entities.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gtufinof on 6/15/18.
 */

public class FailureResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("results")
    @Expose
    private FailureResults results;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public FailureResults getResults() {
        return results;
    }

    public void setResults(FailureResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "FailureResponse{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }
}
