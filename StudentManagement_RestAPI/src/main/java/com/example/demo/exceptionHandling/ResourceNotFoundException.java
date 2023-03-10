package com.example.demo.exceptionHandling;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

/* @ResponseStatus marks a exception class with the status code and reason message that should be returned. 
 * The status code is applied to the HTTP response when the handler method is invoked, 
 * or whenever the specified exception is thrown.
 * In this class, public getter methods used and 
 * constructor injection is used to set the values for private attributes*/
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String resourceName;
	private String fieldName;
	private Object fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : '%s' ", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	
	public String getFieldName() {
		return fieldName;
	}
	
	public Object getFieldValue() {
		return fieldValue;
	}
}
