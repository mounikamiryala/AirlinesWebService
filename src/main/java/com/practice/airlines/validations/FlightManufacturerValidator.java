package com.practice.airlines.validations;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlightManufacturerValidator implements ConstraintValidator<FlightManufacturer,String>{
	
	List<String> approvedmanufacturers =Arrays.asList("Aa","Bb","Cc","Dd");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value==null||value.isBlank())
				return false;
 		if (approvedmanufacturers.contains(value))
 			return true;
 		else 
 			return false;	
	
	}

}
