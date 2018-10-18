package com.app.sgmv.sgmv.services;

import com.app.sgmv.sgmv.entities.failure.FailureReport;
import com.app.sgmv.sgmv.entities.failure.FailureRequest;
import com.app.sgmv.sgmv.entities.failure.FailureResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by gtufinof on 6/14/18.
 */

public interface FailureReportInterface {

    @POST("FailureReport/")
    Call<FailureResponse> registerFailureReport(
            @Body FailureRequest failureRequest
    );

    @GET("FailureReport/")
    Call<FailureResponse> getFailuresReports();

    @POST("FailureReport/")
    Call<FailureResponse> getFailureById(
            @Body FailureReport failureReport
    );

}
