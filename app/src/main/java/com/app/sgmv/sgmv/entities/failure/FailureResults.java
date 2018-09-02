package com.app.sgmv.sgmv.entities.failure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by gtufinof on 6/16/18.
 */

public class FailureResults {

    @SerializedName("failures")
    @Expose
    private List<FailureReport> failureReports;

    public List<FailureReport> getFailureReports() {
        return failureReports;
    }

    public void setFailureReports(List<FailureReport> failureReports) {
        this.failureReports = failureReports;
    }

    @Override
    public String toString() {
        return "FailureResults{" +
                "failureReports=" + failureReports +
                '}';
    }
}
