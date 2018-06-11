package com.app.sgmv.sgmv.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("detail")
    @Expose
    private String detail;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Status{" +
                "code=" + code +
                ", detail='" + detail + '\'' +
                '}';
    }
}
