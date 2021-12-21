package com.practice.airlines.exceptions;

import java.util.List;

public class ApiErrorResponseBuilder {

	private static ApiErrorResponseBuilder builder;
	
	private ApiErrorResponseBuilder(){
		
	}
	private String errorid;
	private Integer status;
	private String message;
	private List<String> errors;
	private String path;
	
	public synchronized static ApiErrorResponseBuilder getInstance() {
		if(builder==null) {
			builder = new ApiErrorResponseBuilder();
		}
		return builder;
	}
	
	public ApiErrorResponseBuilder withErrorId(String errorId) {
		builder.errorid= errorId;
		return builder;
	}
	
	public ApiErrorResponseBuilder withStatus(Integer status) {
		builder.status= status;
		return builder;
	}

	
	public ApiErrorResponseBuilder withMessage(String message) {
		builder.message= message;
		return builder;
	}

	public ApiErrorResponseBuilder withErrors(List<String> errors) {
		builder.errors= errors;
		return builder;
	}
	
	public ApiErrorResponseBuilder forPath(String path) {
		builder.path= path;
		return builder;
	}
	
	
	public ApiErrorResponse build() {
		ApiErrorResponse response = new ApiErrorResponse();
		response.setErrorid(errorid);
		response.setErrors(errors);
		response.setMessage(message);
		response.setPath(path);
		response.setStatus(status);
		return response;
	}
		

}
