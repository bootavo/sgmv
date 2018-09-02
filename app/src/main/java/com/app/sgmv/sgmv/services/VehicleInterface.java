package com.app.sgmv.sgmv.services;

import com.app.sgmv.sgmv.entities.vehicle.Vehicle;
import com.app.sgmv.sgmv.entities.vehicle.VehicleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gtufinof on 6/17/18.
 */

public interface VehicleInterface {

    @GET("Vehicles/")
    Call<VehicleResponse> getVehicles();

    @POST("Vehicles/")
    Call<VehicleResponse> registerVehicle(
            @Body Vehicle vehicle
            );

    @GET("Tracts/")
    Call<VehicleResponse> getTracts();

    @GET("Semitrailers/")
    Call<VehicleResponse> getSemitracts();

}
