package com.app.sgmv.sgmv.entities.vehicle;

import com.app.sgmv.sgmv.entities.Status;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gtufinof on 6/17/18.
 */

public class VehicleResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("results")
    @Expose
    private VehicleResults results;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public VehicleResults getResults() {
        return results;
    }

    public void setResults(VehicleResults results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "VehicleResponse{" +
                "status=" + status +
                ", results=" + results +
                '}';
    }
}
