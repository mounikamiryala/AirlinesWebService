package com.practice.airlines.controller;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.practice.airlines.pojo.FlightDetailsPojo;
import com.practice.airlines.service.FlightService;


@RestController
//the below annotation enables validation on the path variables.
@Validated
public class MainController {
	
	@Autowired
    FlightService flightService;
	
	//HTTP GET method i.e. to retrieve the details
	//GetMapping(path="/flight") or the below annotation also works.
	@RequestMapping(method =RequestMethod.GET,path = "/flight/{id}")
	public FlightDetailsPojo getDetails(@PathVariable(name="id") @Positive Integer flightId) {
		FlightDetailsPojo pojo= flightService.getDetails(flightId);
		return pojo;
	}
	
	//HTTP POST method i.e. to record the details
	//@RequestMapping(method =RequestMethod.PUT,path = "/flight") or the below annotation also works
	@PostMapping(path="/flight")
	public FlightDetailsPojo recordDetails(@RequestBody @Valid FlightDetailsPojo pojo) {
		
			return flightService.recordDetails(pojo);
	}
	
	//HTTP PUT method i.e. to modify the details 
	//@RequestMapping(method =RequestMethod.PUT,path = "/flight") or the below annotation also works

	@PutMapping(path="/flight")
	public FlightDetailsPojo modifydDetails(@RequestBody FlightDetailsPojo pojo) {
		FlightDetailsPojo update= flightService.modifyDetails(pojo);
		return update;
		}

	//HTTP DELETE method i.e. to delete the details
	//@RequestMapping(method =RequestMethod.DELETE,path = "/flight") or the below annotation also works

	@DeleteMapping(path="/flight/delete/{id}")
	public String deleteDetails(@PathVariable(name="id")@Positive Integer flightId) {
			return "Deleted the details succesfully.";
		}
	
	// GET method i.e. to retrieve all flight details 
	@RequestMapping(method =RequestMethod.GET,path = "/flight")
	public List<FlightDetailsPojo> getAllDetails() {
		List<FlightDetailsPojo> pojo= flightService.getAllDetails();
		return pojo;
	}
	
	// GET method i.e. to retrieve flight details using given Flight model
 	@RequestMapping(method =RequestMethod.GET,path = "/flight/model/{model}")
	public FlightDetailsPojo getFlightDetailsByModel(@PathVariable(name="model") String model) {
		FlightDetailsPojo pojo= flightService.getFlightDetailsByModel(model);
		return pojo;
	}
	
}
