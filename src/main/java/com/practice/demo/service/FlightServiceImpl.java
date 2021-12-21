package com.practice.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.demo.entity.FlightDetails;
import com.practice.demo.pojo.FlightDetailsPojo;
import com.practice.demo.repository.FlightRepository;


@Service
public class FlightServiceImpl implements FlightService{
	@Autowired
	FlightRepository repo;
	
	/* 
	 * Create the flight details operation in the service layer.
	 */
	@Override
	public FlightDetailsPojo recordDetails(FlightDetailsPojo data) {
	
		FlightDetails flightDetails= new FlightDetails();
		// copying the input from the front-end i.e response body in the request of the PUT method into the entity file
		BeanUtils.copyProperties(data, flightDetails);
		// save the values in the table and generate the unique id for the flights
		flightDetails=repo.save(flightDetails);
		// copying the input from the table along with the id and return the same pojo to the user in the fornt-end i.e request body.
		BeanUtils.copyProperties(flightDetails,data);
		// return the pojo
		System.out.println("Successfully recorded.");
		return data;
	}
	
	/* 
	 * Read the flight details operation in the service layer.
	 */
	@Override
	public FlightDetailsPojo getDetails(Integer flightId) {
		
		 FlightDetailsPojo pojo= new FlightDetailsPojo(); 
		 //check if the record exists for the given input by querying on flightid
		 Optional<FlightDetails> flightDetails= repo.findById(flightId);
		 //if the record is present
		 	if (flightDetails.isPresent()) {
				// copying the input from the database-table entity file into the  response body of the front-end 
		 		BeanUtils.copyProperties(flightDetails.get(),pojo);
		 	}
		 	//else throws customised exception.
		 	else {
		 		//Throw Exception
		 		}
		 	//return the pojo of the flightdetails
		 	return pojo;
	}
	
	/* Update the flight details operation method in the service layer.
	 * 
	 */
	@Override
	public  FlightDetailsPojo modifyDetails(FlightDetailsPojo data) {
		
		FlightDetailsPojo pojo= new FlightDetailsPojo(); 
		 //check if the record exists for the given input by querying on flightid
		Optional<FlightDetails> flightDetails= repo.findById(data.getId());
		 //if the record is present
		if (flightDetails.isPresent()) {
			FlightDetails flight= new FlightDetails();
			// copying the input from the table along with the id and return the same pojo to the user in the fornt-end i.e request body.
	 		BeanUtils.copyProperties(pojo,flightDetails);
	 		// save the updated data into the table via values copied copied into the entity file.
	 		flight = repo.save(flight);
			// copying the input from the database-table entity file into the  response body of the front-end 
	 		BeanUtils.copyProperties(flightDetails,pojo);
	 	}
	 	//else throws customised exception.
	 	else {
	 		//Throw Exception
	 		}
	 	//return the pojo of the flightdetails
	 	return pojo;
		}

	/* 
	 * Delete the flight details operation in the service layer.
	 */
	@Override
	public  void deleteDetails(Integer flightId) {
		 
		//check if the record exists for the given input by querying on flightid
		 Optional<FlightDetails> flightDetails= repo.findById(flightId);
		 //if the record is present
		 	if (flightDetails.isPresent()) {
		 		// delete the record found in table for the given id in the request body.
		 		repo.deleteById(flightId);	
				 System.out.println("Deleted the record succesfully!");
		 		}
		 	//else throws customised exception.
		 	else {
		 		//throw exception}
		 	} 
		}
	
	
	/*
	 *  Read all of the flight details present in the table in the service layer.
	 */
	@Override
	public List<FlightDetailsPojo> getAllDetails(){
		 Iterable<FlightDetails> flightDetails= repo.findAll();
			List<FlightDetailsPojo> pojoList= StreamSupport.stream(flightDetails.spliterator(), false).map(flight->{
			 FlightDetailsPojo  pojo = new FlightDetailsPojo();
				BeanUtils.copyProperties(flightDetails,pojo);
				return pojo;
		 }).collect(Collectors.toList());
			return pojoList;
	}
	
	/*
	 *  Read the flight details present for given model name.
	 */
	@Override
	public FlightDetailsPojo getFlightDetailsByModel(String model) {
		FlightDetailsPojo pojo = new FlightDetailsPojo();
		FlightDetails flightDetails= repo.findByModel(model);
		BeanUtils.copyProperties(flightDetails,pojo);
		return  pojo;
	}
	
}