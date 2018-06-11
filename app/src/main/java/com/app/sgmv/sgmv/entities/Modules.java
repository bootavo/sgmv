package com.app.sgmv.sgmv.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Modules {

    @SerializedName("modules")
    @Expose
    private String modules;

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "modules='" + modules + '\'' +
                '}';
    }
}
