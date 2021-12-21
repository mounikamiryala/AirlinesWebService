
package com.practice.airlines.pojo;

import javax.annotation.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.airlines.validations.FlightManufacturer;

public class FlightDetailsPojo {

	@NotNull
	private Integer id;
	
	@NotNull
	@PositiveOrZero
	private Integer capacity;
	
	@NotNull
	@FlightManufacturer
	private String manufacturingCompany;
	
	@NotNull
	private String model;
	
	@NotNull
	@Past
	@JsonFormat(pattern="MM/dd/yyyy")
	private Date manufacturedDate;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightDetailsPojo other = (FlightDetailsPojo) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FlightDetailsPojo [id=" + id + ", capacity=" + capacity
				+ ", manufacturingCompany=" + manufacturingCompany + ", model=" + model + ", manufacturedDate="
				+ manufacturedDate + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getManufacturingCompany() {
		return manufacturingCompany;
	}

	public void setManufacturingCompany(String manufacturingCompany) {
		this.manufacturingCompany = manufacturingCompany;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

}
