package com.healthcare.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.Dao.EnrolleeDao;
import com.healthcare.entity.Enrollee;
import com.healthcare.service.EnrolleeService;

@RestController
public class EnrolleeController {

	@Autowired
	private EnrolleeDao newEnrolleDao;
	@Autowired
	private EnrolleeService newEnrolleeService;
	
	//reading a enrollee
	@GetMapping("/enrollee/{enrolleeId}")
	public Optional<Enrollee> readEnrollee(@PathVariable int enrolleeId)
	{
		return newEnrolleDao.findById(enrolleeId);
	}
	
	//adding a new enrollee
	@PostMapping("/enrollee")
	public ResponseEntity<?> createNewEnrollee(@RequestBody Enrollee theEnrolle) 
	{
		 return newEnrolleeService.createEnrollee(theEnrolle);
	}
	
	//updating a new enrollee
	@PutMapping("/enrollee")
	public ResponseEntity<?> updateEnrollee(@RequestBody Enrollee theEnrolle) 
	{
		return newEnrolleeService.updateEnrollee(theEnrolle);	
	}
	
	//Deleting an existing enrollee
	@DeleteMapping("/enrollee/{enrolleeId}")
	public ResponseEntity<?> deleteEnrollee(@PathVariable int enrolleeId)
	{
		return newEnrolleeService.deleteEnrollee(enrolleeId);
	}
		
}
