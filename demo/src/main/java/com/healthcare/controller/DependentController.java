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

import com.healthcare.entity.Dependent;
import com.healthcare.service.DependentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DependentController {

	@Autowired
	private DependentService newDependentService;
	
		@PostMapping("/dependent")
		public List<ResponseEntity<?>> createNewDependent(@RequestBody Dependent[] newDependent)
		{
			log.info("Inside Dependent controller createNewDependent");
			return newDependentService.createNewDependent(newDependent);	
		}
	
		@PutMapping("/dependent")
		public ResponseEntity<?> updateDependent(@RequestBody Dependent newDependent)
		{
			log.info("Inside Dependent controller updateDependent");
			return newDependentService.updateDependent(newDependent);
		}
		
		@DeleteMapping("/dependent/{enrolleeID}/{dependentID}")
		public ResponseEntity<?> deleteDependent(@PathVariable int enrolleeID,@PathVariable int dependentID)
		{
			log.info("Inside Dependent controller deleteDependent");
			return newDependentService.deleteDependent( enrolleeID, dependentID);
		}	
}
