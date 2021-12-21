package com.practice.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.demo.entity.FlightDetails;


@Repository
public interface FlightRepository extends JpaRepository<FlightDetails, Integer>{

	FlightDetails findByModel(String model);
	
}
