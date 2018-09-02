package com.app.sgmv.sgmv.entities.vehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Vehicle implements Serializable{

	@SerializedName("sgmv_vehicle_id")
	@Expose
	private int sgmv_vehicle_id;

	@SerializedName("placa")
	@Expose
	private String placa;

	@SerializedName("brand")
	@Expose
	private String brand;

	@SerializedName("model")
	@Expose
	private String model;

	@SerializedName("classes")
	@Expose
	private String classes;

	@SerializedName("constancy")
	@Expose
	private String constancy;

	@SerializedName("category")
	@Expose
	private String category;

	@SerializedName("axis_number")
	@Expose
	private String axis_number;

	@SerializedName("chassis_series")
	@Expose
	private String chassis_series;

	@SerializedName("year_production")
	@Expose
	private String year_production;

	@SerializedName("useful_load")
	@Expose
	private String useful_load;

	@SerializedName("weight_dry")
	@Expose
	private String weight_dry;

	@SerializedName("km_total")
	@Expose
	private float km_total;

	@SerializedName("status")
	@Expose
	private String status;

	public int getSgmv_vehicle_id() {
		return sgmv_vehicle_id;
	}

	public void setSgmv_vehicle_id(int sgmv_vehicle_id) {
		this.sgmv_vehicle_id = sgmv_vehicle_id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getConstancy() {
		return constancy;
	}

	public void setConstancy(String constancy) {
		this.constancy = constancy;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAxis_number() {
		return axis_number;
	}

	public void setAxis_number(String axis_number) {
		this.axis_number = axis_number;
	}

	public String getChassis_series() {
		return chassis_series;
	}

	public void setChassis_series(String chassis_series) {
		this.chassis_series = chassis_series;
	}

	public String getYear_production() {
		return year_production;
	}

	public void setYear_production(String year_production) {
		this.year_production = year_production;
	}

	public String getUseful_load() {
		return useful_load;
	}

	public void setUseful_load(String useful_load) {
		this.useful_load = useful_load;
	}

	public String getWeight_dry() {
		return weight_dry;
	}

	public void setWeight_dry(String weight_dry) {
		this.weight_dry = weight_dry;
	}

	public float getKm_total() {
		return km_total;
	}

	public void setKm_total(float km_total) {
		this.km_total = km_total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Vehicle{" +
				"sgmv_vehicle_id=" + sgmv_vehicle_id +
				", placa='" + placa + '\'' +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", classes='" + classes + '\'' +
				", constancy='" + constancy + '\'' +
				", category='" + category + '\'' +
				", axis_number='" + axis_number + '\'' +
				", chassis_series='" + chassis_series + '\'' +
				", year_production='" + year_production + '\'' +
				", useful_load='" + useful_load + '\'' +
				", weight_dry='" + weight_dry + '\'' +
				", km_total=" + km_total +
				", status='" + status + '\'' +
				'}';
	}
}
