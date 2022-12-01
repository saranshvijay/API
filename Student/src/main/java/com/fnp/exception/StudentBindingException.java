package com.fnp.exception;

public class StudentBindingException  extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public StudentBindingException() {}
	
	public StudentBindingException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return "StudentBindingException [message=" + message + "]";
	}
	
	
}
