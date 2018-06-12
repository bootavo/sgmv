package com.app.sgmv.sgmv.services;

import com.app.sgmv.sgmv.entities.LoginResponse;
import com.app.sgmv.sgmv.entities.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginInterface {

    @POST("Login/")
    Call<LoginResponse> login(
            @Body User user
    );

}
