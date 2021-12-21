package com.practice.demo.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="flightdetails")
public class FlightDetails {

	@Id
	@Column(name="flight_id")
	//Uncomment it if we want to auto increment the id value generation,now I want to directly insert the records along with the id.
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name="flight_capacity",nullable=false)
	private Integer capacity;
	
	@Column(name="mfg_company",nullable=false)
	private String manufacturingCompany;
	
	@Column(name="flight_model",nullable=false)
	private String model;
	
	@Column(name="mfg_date",nullable=false)
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
		FlightDetails other = (FlightDetails) obj;
		return Objects.equals(id, other.id);
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

	public Date getManufacturedDate() {
		return manufacturedDate;
	}

	public void setManufacturedDate(Date manufacturedDate) {
		this.manufacturedDate = manufacturedDate;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
