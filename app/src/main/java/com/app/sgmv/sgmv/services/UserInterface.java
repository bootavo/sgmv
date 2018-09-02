package com.app.sgmv.sgmv.services;

import com.app.sgmv.sgmv.entities.user.LoginResponse;
import com.app.sgmv.sgmv.entities.user.User;
import com.app.sgmv.sgmv.entities.user.UsersResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {

    @POST("Login/")
    Call<LoginResponse> login(
            @Body User user
    );

    @GET("Drivers/")
    Call<UsersResponse> getDrivers();

    @GET("Employees/")
    Call<UsersResponse> getEmployees();

    @POST("Employees/")
    Call<UsersResponse> registerUser(
        @Body User user
    );

}
