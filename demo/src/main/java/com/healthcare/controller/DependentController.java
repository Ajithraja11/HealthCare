package com.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.entity.Dependent;
import com.healthcare.service.DependentService;

@RestController
public class DependentController {

	@Autowired
	private DependentService newDependentService;
	
	//adding a new dependent to an enrollee
		@PostMapping("/dependent")
		public ResponseEntity<?> createNewDependent(@RequestBody Dependent newDependent)
		{
			return newDependentService.createNewDependent(newDependent);
			
		}
	//updating a dependent from a enrollee
		@PutMapping("/dependent")
		public ResponseEntity<?> updateDependent(@RequestBody Dependent newDependent)
		{
			return newDependentService.updateDependent(newDependent);
		}
		
	//deleting a depedent from a enrollee
		@DeleteMapping("/dependent/{enrolleeID}/{dependentID}")
		public ResponseEntity<?> deleteDependent(@PathVariable int enrolleeID,@PathVariable int dependentID)
		{
			return newDependentService.deleteDependent( enrolleeID, dependentID);
		}	
}
