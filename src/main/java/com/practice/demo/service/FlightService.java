package com.practice.demo.service;


import java.util.List;

import com.practice.demo.pojo.FlightDetailsPojo;

public interface FlightService {
	
	//Create the flight details
	public  FlightDetailsPojo recordDetails(FlightDetailsPojo data);
	
	// Read the flight details
	public FlightDetailsPojo getDetails(Integer flightId);
	
	// Update the flight details	 
	public  FlightDetailsPojo modifyDetails(FlightDetailsPojo data);
	
	// Delete the flight details
	public  void deleteDetails(Integer flightId);

	// Read all of the flight details present in the table.
	 public List<FlightDetailsPojo> getAllDetails();
	 
	// Read the flight details for given model name.
	public FlightDetailsPojo getFlightDetailsByModel(String model);

	 
}


