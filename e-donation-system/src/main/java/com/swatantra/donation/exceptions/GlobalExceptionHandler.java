package com.swatantra.donation.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.swatantra.donation.exceptions.model.ExceptionMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidDataException.class)
	public ResponseEntity<ExceptionMessage> handler(InvalidDataException e){
		ExceptionMessage exception = new ExceptionMessage(e.getMessage(),System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DonorNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handler(DonorNotFoundException e){
		ExceptionMessage exception = new ExceptionMessage(e.getMessage(),System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NgoNotFoundException.class)
	public ResponseEntity<ExceptionMessage> handler(NgoNotFoundException e){
		ExceptionMessage exception = new ExceptionMessage(e.getMessage(),System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}

}
