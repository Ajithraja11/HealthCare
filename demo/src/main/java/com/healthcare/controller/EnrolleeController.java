package com.healthcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entity.Enrollee;
import com.healthcare.service.EnrolleeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EnrolleeController {

	@Autowired
	private EnrolleeService newEnrolleeService;
	
	@PostMapping("/enrollee")
	public List<ResponseEntity<?>> createNewEnrollee(@RequestBody Enrollee[] theEnrolle) 
	{	
		log.info("inside controller createNewEnrollee");
		 return newEnrolleeService.createEnrollee(theEnrolle);
	}
	
	@PutMapping("/enrollee")
	public ResponseEntity<?> updateEnrollee(@RequestBody Enrollee theEnrolle) 
	{
		log.info("inside controller updateEnrollee");
		return newEnrolleeService.updateEnrollee(theEnrolle);	
	}
	
	@DeleteMapping("/enrollee/{enrolleeId}")
	public ResponseEntity<?> deleteEnrollee(@PathVariable int enrolleeId)
	{
		log.info("inside controller deleteEnrollee");
		return newEnrolleeService.deleteEnrollee(enrolleeId);
	}
		
}
