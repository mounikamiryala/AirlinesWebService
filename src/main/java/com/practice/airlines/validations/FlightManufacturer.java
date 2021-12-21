package com.practice.airlines.validations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target(FIELD)
// the below annotation says that this annotation is validated by the class given.
@Constraint(validatedBy=FlightManufacturerValidator.class)
public @interface FlightManufacturer {
	
	public String message() default "The manufacturer value provided is invalid.";
	public Class<?>[] groups() default{};
	public Class<?extends Payload>[] payload() default{};

}
