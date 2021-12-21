package com.practice.airlines.exceptions;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.actuate.trace.http.HttpTrace.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.practice.airlines")
//handling the exceptions thrown by the application
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	// for handling the exceptions thrown for validations on the parameters
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
			System.out.println("handleMethodArgumentNotValid..");
			List<String> errors = ex.getBindingResult().getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.toList());
			HttpServletRequest req= ((ServletWebRequest)request).getRequest();
			ApiErrorResponse apiError = ApiErrorResponseBuilder.getInstance()
					.withErrorId("Airlines"+LocalDateTime.now(ZoneOffset.UTC))
					.forPath(req.getRequestURI())
					.withMessage(ex.getMessage())
					.withErrors(errors)
					.withStatus(status.value())
					.build();
			return new ResponseEntity<Object>(apiError,headers,status);
	}
	
	//for throwing errors when no records found for the given requests.
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ApiErrorResponse> handleResponseStatusException(ResponseStatusException ex,HttpServletRequest req){
		System.out.println("handleResponseStatusException..");
		
		List<String> errors = Arrays.asList(ex.getReason());
		
		ApiErrorResponse apiError = ApiErrorResponseBuilder.getInstance()
				.withErrorId("Airlines"+LocalDateTime.now(ZoneOffset.UTC))
				.forPath(req.getRequestURI())
				.withMessage(ex.getMessage())
				.withErrors(errors)
				.withStatus(ex.getStatus().value())
				.build();
		return new ResponseEntity<ApiErrorResponse>(apiError,ex.getStatus());
	}
	
	//for throwing errors when constraint violations i.e in the path parameters
		@ExceptionHandler(ConstraintViolationException.class)
		public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException ex,HttpServletRequest req){
			System.out.println("handleConstraintViolationException..");
			
			List<String> errors = new ArrayList<>();

		    //for (ConstraintViolation<?> violation : ex.getConstraintName()) {
				//errors.add(violation.getMessage());
			//}
			ApiErrorResponse apiError = ApiErrorResponseBuilder.getInstance()
					.withErrorId("Airlines"+LocalDateTime.now(ZoneOffset.UTC))
					.forPath(req.getRequestURI())
					.withMessage(ex.getMessage())
					.withErrors(errors)
					.withStatus(HttpStatus.BAD_REQUEST.value())
					.build();
			return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.BAD_REQUEST);
		}
	
	//Global exceptions like null pointers.
	@ExceptionHandler(Exception.class)
	public 	ResponseEntity<ApiErrorResponse> handleException(Exception ex,HttpServletRequest req){
		
		System.out.println("handleException..");
		
		List<String> errors = Arrays.asList(ex.getMessage());
		
				ApiErrorResponse apiError = ApiErrorResponseBuilder.getInstance()
				.withErrorId("Airlines"+LocalDateTime.now(ZoneOffset.UTC))
				.forPath(req.getRequestURI())
				.withMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.withErrors(errors)
				.withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.build();
		return new ResponseEntity<ApiErrorResponse>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
