package com.app.sgmv.sgmv.entities.failure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gtufinof on 6/14/18.
 */

public class FailureReport implements Serializable{

    @SerializedName("sgmv_failure_report_id")
    @Expose
    private int sgmv_failure_report_id;

    @SerializedName("cod_report")
    @Expose
    private String cod_report;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("mileage")
    @Expose
    private float mileage;

    @SerializedName("observations")
    @Expose
    private String observations;

    @SerializedName("sgmv_tract_id")
    @Expose
    private int sgmv_tract_id;

    @SerializedName("sgmv_semitrailer_id")
    @Expose
    private int sgmv_semitrailer_id;

    @SerializedName("sgmv_employee_id")
    @Expose
    private int sgmv_employee_id;

    @SerializedName("sgmv_driver_id")
    @Expose
    private int sgmv_driver_id;

    @SerializedName("status")
    @Expose
    private String status;

    public int getSgmv_failure_report_id() {
        return sgmv_failure_report_id;
    }

    public void setSgmv_failure_report_id(int sgmv_failure_report_id) {
        this.sgmv_failure_report_id = sgmv_failure_report_id;
    }

    public String getCod_report() {
        return cod_report;
    }

    public void setCod_report(String cod_report) {
        this.cod_report = cod_report;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public int getSgmv_tract_id() {
        return sgmv_tract_id;
    }

    public void setSgmv_tract_id(int sgmv_tract_id) {
        this.sgmv_tract_id = sgmv_tract_id;
    }

    public int getSgmv_semitrailer_id() {
        return sgmv_semitrailer_id;
    }

    public void setSgmv_semitrailer_id(int sgmv_semitrailer_id) {
        this.sgmv_semitrailer_id = sgmv_semitrailer_id;
    }

    public int getSgmv_employee_id() {
        return sgmv_employee_id;
    }

    public void setSgmv_employee_id(int sgmv_employee_id) {
        this.sgmv_employee_id = sgmv_employee_id;
    }

    public int getSgmv_driver_id() {
        return sgmv_driver_id;
    }

    public void setSgmv_driver_id(int sgmv_driver_id) {
        this.sgmv_driver_id = sgmv_driver_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FailureReportInterface{" +
                "sgmv_failure_report_id=" + sgmv_failure_report_id +
                ", cod_report='" + cod_report + '\'' +
                ", date='" + date + '\'' +
                ", mileage=" + mileage +
                ", observations='" + observations + '\'' +
                ", sgmv_tract_id=" + sgmv_tract_id +
                ", sgmv_semitrailer_id=" + sgmv_semitrailer_id +
                ", sgmv_employee_id=" + sgmv_employee_id +
                ", sgmv_driver_id=" + sgmv_driver_id +
                ", status='" + status + '\'' +
                '}';
    }

    public static List<FailureReport> setData(){

        List<FailureReport> failureReports = new ArrayList<>();
        FailureReport failureReport = null;
        for (int i = 0; i<15; i++){
            failureReport = new FailureReport();
            failureReport.setCod_report("SGMV-F00"+i);
            failureReport.setObservations("El trailer "+failureReport.getCod_report()+" estaba averiado");
            failureReport.setDate("15/06/2018");
            failureReports.add(failureReport);
        }
        return failureReports;
    }

}
