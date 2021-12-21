package com.practice.airlines.repository;

import org.springframework.stereotype.Repository;

import com.practice.airlines.entity.FlightDetails;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface FlightRepository extends JpaRepository<FlightDetails, Integer>{

	// find flight details query by given model name.
	FlightDetails findByModel(String model);
	
}
