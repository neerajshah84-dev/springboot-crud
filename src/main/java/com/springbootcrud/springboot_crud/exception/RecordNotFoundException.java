package com.springbootcrud.springboot_crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException{
	
	public RecordNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
