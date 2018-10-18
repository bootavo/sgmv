package com.app.sgmv.sgmv.entities.maintenance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by gtufinof on 6/24/18.
 */

public class OrdenMaintenance implements Serializable{

    @SerializedName("sgmv_orden_maintenance_id")
    @Expose
    private int sgmv_orden_maintenance_id;

    @SerializedName("cod_maintenance_order")
    @Expose
    private String cod_maintenance_order;

    @SerializedName("kind_maintenance")
    @Expose
    private String kind_maintenance; // P, C

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("hour")
    @Expose
    private String hour;

    @SerializedName("mileage")
    @Expose
    private String mileage;

    @SerializedName("priority")
    @Expose
    private String priority; // N, U, E

    @SerializedName("sgmv_failure_report_id")
    @Expose
    private int sgmv_failure_report_id;

    @SerializedName("sgmv_employee_id")
    @Expose
    private int sgmv_employee_id;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("activities")
    @Expose
    private String activities;

    @SerializedName("defects")
    @Expose
    private String defects;

    @SerializedName("price")
    @Expose
    private float price;

    public int getSgmv_orden_maintenance_id() {
        return sgmv_orden_maintenance_id;
    }

    public void setSgmv_orden_maintenance_id(int sgmv_orden_maintenance_id) {
        this.sgmv_orden_maintenance_id = sgmv_orden_maintenance_id;
    }

    public String getCod_maintenance_order() {
        return cod_maintenance_order;
    }

    public void setCod_maintenance_order(String cod_maintenance_order) {
        this.cod_maintenance_order = cod_maintenance_order;
    }

    public String getKind_maintenance() {
        return kind_maintenance;
    }

    public void setKind_maintenance(String kind_maintenance) {
        this.kind_maintenance = kind_maintenance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getSgmv_failure_report_id() {
        return sgmv_failure_report_id;
    }

    public void setSgmv_failure_report_id(int sgmv_failure_report_id) {
        this.sgmv_failure_report_id = sgmv_failure_report_id;
    }

    public int getSgmv_employee_id() {
        return sgmv_employee_id;
    }

    public void setSgmv_employee_id(int sgmv_employee_id) {
        this.sgmv_employee_id = sgmv_employee_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrdenMaintenance{" +
                "sgmv_orden_maintenance_id=" + sgmv_orden_maintenance_id +
                ", cod_maintenance_order='" + cod_maintenance_order + '\'' +
                ", kind_maintenance='" + kind_maintenance + '\'' +
                ", date='" + date + '\'' +
                ", hour='" + hour + '\'' +
                ", mileage='" + mileage + '\'' +
                ", priority='" + priority + '\'' +
                ", sgmv_failure_report_id=" + sgmv_failure_report_id +
                ", sgmv_employee_id=" + sgmv_employee_id +
                ", status='" + status + '\'' +
                ", activities='" + activities + '\'' +
                ", defects='" + defects + '\'' +
                ", price=" + price +
                '}';
    }
}
