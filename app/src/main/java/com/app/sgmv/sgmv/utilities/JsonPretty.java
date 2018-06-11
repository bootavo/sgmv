package com.app.sgmv.sgmv.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Response;

/**
 * Created by bootavo on 29/08/2017.
 */

public class JsonPretty {

    public static String getPrettyJson(Response response){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(response.body());
        return json;
    }

}
