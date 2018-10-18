package com.app.sgmv.sgmv.entities.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable{

    @SerializedName("sgmv_employee_id")
    @Expose
    private int sgmv_employee_id;

    @SerializedName("sgmv_user_id")
    @Expose
    private int sgmv_user_id;

    @SerializedName("user")
    @Expose
    private String user;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("names")
    @Expose
    private String names;

    @SerializedName("last_name")
    @Expose
    private String last_name;

    @SerializedName("dni")
    @Expose
    private String dni;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile_phone")
    @Expose
    private String mobile_phone;

    @SerializedName("photo")
    @Expose
    private String photo;

    @SerializedName("rol")
    @Expose
    private String rol;

    @SerializedName("sgmv_rol_id")
    @Expose
    private int sgmv_rol_id;

    @SerializedName("status")
    @Expose
    private String status;

    public int getSgmv_employee_id() {
        return sgmv_employee_id;
    }

    public void setSgmv_employee_id(int sgmv_employee_id) {
        this.sgmv_employee_id = sgmv_employee_id;
    }

    public int getSgmv_user_id() {
        return sgmv_user_id;
    }

    public void setSgmv_user_id(int sgmv_user_id) {
        this.sgmv_user_id = sgmv_user_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSgmv_rol_id() {
        return sgmv_rol_id;
    }

    public void setSgmv_rol_id(int sgmv_rol_id) {
        this.sgmv_rol_id = sgmv_rol_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "sgmv_employee_id=" + sgmv_employee_id +
                ", sgmv_user_id=" + sgmv_user_id +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", names='" + names + '\'' +
                ", last_name='" + last_name + '\'' +
                ", dni='" + dni + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", photo='" + photo + '\'' +
                ", rol='" + rol + '\'' +
                ", sgmv_rol_id=" + sgmv_rol_id +
                ", status='" + status + '\'' +
                '}';
    }
}
