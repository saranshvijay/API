package com.fnp.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
public ResponseEntity<Object> exception(MethodArgumentNotValidException e){
		
		BindingResult bindingResult = e.getBindingResult();
		
		List<FieldError> errorList = bindingResult.getFieldErrors();
		
		return new ResponseEntity<Object>("could not bind. " + errorList, HttpStatus.BAD_REQUEST);
	}
}
