package com.practice.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.practice.demo.pojo.FlightDetailsPojo;
import com.practice.demo.service.FlightService;
@RestController
public class MainController {
	
	@Autowired
    FlightService flightService;
	
	//HTTP GET method i.e. to retrieve the details
	//Getmapping(path="/flight") or the below annotation also works.
	@RequestMapping(method =RequestMethod.GET,path = "/flight/{id}")
	public FlightDetailsPojo getDetails(@PathVariable(name="id") Integer flightId) {
		FlightDetailsPojo pojo= flightService.getDetails(flightId);
		return pojo;
	}
	
	//HTTP POST method i.e. to record the details
	//@RequestMapping(method =RequestMethod.PUT,path = "/flight") or the below annotation also works
	@PostMapping(path="/flight")
	public FlightDetailsPojo recordDetails(@RequestBody FlightDetailsPojo pojo) {
		
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
	public String deleteDetails(@PathVariable(name="id") Integer flightId) {
			return "Deleted the details succesfully.";
		}
	
	// GET method i.e. to retrieve all flight details 
	@RequestMapping(method =RequestMethod.GET,path = "/flight")
	public List<FlightDetailsPojo> getAllDetails() {
		List<FlightDetailsPojo> pojo= flightService.getAllDetails();
		return pojo;
	}
	
	// GET method i.e. to retrieve flight details using Flight model
 	@RequestMapping(method =RequestMethod.GET,path = "/flight/{model}")
	public FlightDetailsPojo getFlightDetailsByModel(@PathVariable(name="model") String model) {
		FlightDetailsPojo pojo= flightService.getFlightDetailsByModel(model);
		return pojo;
	}
	
}
