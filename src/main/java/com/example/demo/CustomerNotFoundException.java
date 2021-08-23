package com.example.demo;

public class CustomerNotFoundException extends Exception{
	private Long id;
	public CustomerNotFoundException(Long id) {
	        super(String.format("Customer is not found with id : '%s'", id));
	        }
}
