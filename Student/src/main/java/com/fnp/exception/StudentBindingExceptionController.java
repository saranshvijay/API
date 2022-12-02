package com.fnp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentBindingExceptionController {
	
	@ExceptionHandler(value = StudentBindingException.class)
	public ResponseEntity<Object> exeption(StudentBindingException e){
		
		return new ResponseEntity<Object>("could not bind " + e, HttpStatus.BAD_REQUEST);
	}
	

}
