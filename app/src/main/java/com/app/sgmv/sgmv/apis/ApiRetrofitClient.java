package com.app.sgmv.sgmv.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bootavo on 26/08/2017.
 */

public class ApiRetrofitClient {

    public static final String BASE_URL = "http://190.117.136.82:8080/sgmv/";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(OkHttpApiClient.getOkHttpClient())
                    //.addConverterFactory(ScalarsConverterFactory.create())
                    //.addConverterFactory(GsonConverterFactory.create(gson))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
