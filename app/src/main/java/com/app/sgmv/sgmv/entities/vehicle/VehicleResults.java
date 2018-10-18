package com.app.sgmv.sgmv.entities.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gtufinof on 6/17/18.
 */

public class VehicleResults {

    @SerializedName("vehicles")
    @Expose
    private List<Vehicle> vehicles;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "VehicleResults{" +
                "vehicles=" + vehicles +
                '}';
    }
}
