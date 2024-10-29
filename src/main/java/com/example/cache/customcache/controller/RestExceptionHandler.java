package com.example.cache.customcache.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.customcache.entity.CacheError;
import com.example.cache.customcache.exception.EmployeeAddException;
import com.example.cache.customcache.exception.EmployeeDeleteException;
import com.example.cache.customcache.exception.EmployeeRetrieveException;

@RestController
@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = EmployeeRetrieveException.class)
	public ResponseEntity handleRetrieveException(){
		
		CacheError cacheError = new CacheError(404, "Unable to retrieve Data");
		
		return new ResponseEntity<CacheError>(cacheError, HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(value = EmployeeAddException.class)
	public ResponseEntity handleAddException(){
		
		CacheError cacheError = new CacheError(422, "Failed to insert Data");
		
		return new ResponseEntity<CacheError>(cacheError, HttpStatus.UNPROCESSABLE_ENTITY);	
	}
	
	@ExceptionHandler(value = EmployeeDeleteException.class)
	public ResponseEntity handleDeleteException(){
		
		CacheError cacheError = new CacheError(404, "Failed to remove the Data");
		
		return new ResponseEntity<CacheError>(cacheError, HttpStatus.NOT_FOUND);	
	}
}
