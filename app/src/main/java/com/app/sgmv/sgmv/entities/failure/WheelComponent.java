package com.app.sgmv.sgmv.entities.failure;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gtufinof on 6/14/18.
 */

public class WheelComponent {

    @SerializedName("sgmv_failure_report_id")
    @Expose
    private int sgmv_failure_report_id;

    @SerializedName("sgmv_wheel_component_id")
    @Expose
    private int sgmv_wheel_component_id;

    @SerializedName("description")
    @Expose
    private String description;

    public int getSgmv_failure_report_id() {
        return sgmv_failure_report_id;
    }

    public void setSgmv_failure_report_id(int sgmv_failure_report_id) {
        this.sgmv_failure_report_id = sgmv_failure_report_id;
    }

    public int getSgmv_wheel_component_id() {
        return sgmv_wheel_component_id;
    }

    public void setSgmv_wheel_component_id(int sgmv_wheel_component_id) {
        this.sgmv_wheel_component_id = sgmv_wheel_component_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "WheelComponent{" +
                "sgmv_failure_report_id=" + sgmv_failure_report_id +
                ", sgmv_wheel_component_id=" + sgmv_wheel_component_id +
                ", description='" + description + '\'' +
                '}';
    }
}
