package com.healthcare.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Dependent;
import com.healthcare.repository.DependentDaoImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DependentService {

	@Autowired
	private DependentDaoImpl newDependentDaoImpl;
	
	public ResponseEntity<?> createNewDependent(Dependent newDependent)
	{
		if(validateDependent(newDependent))
			return newDependentDaoImpl.createNewDependent(newDependent);
		else
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> updateDependent(Dependent newDependent)
	{
		if(validateDependent(newDependent))
			return newDependentDaoImpl.updateDependent(newDependent);
		else
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> deleteDependent( int enrolleeID, int dependentID)
	{
		return newDependentDaoImpl.deleteDependent(enrolleeID, dependentID);
	}
	
	public boolean validateDependent(Dependent newDependent)
	{
		if(newDependent.getName()==null || newDependent.getName().trim().length()==0)
		{
			log.error("Dependent Name Must be provided");
			return false;
		}
		if(newDependent.getBirthDate()==null)
		{
			log.error("Invalid Dependent Birthdate provided");
			return false;
		}
		if(Pattern.matches(".*[a-zA-Z&%^$@!].*",newDependent.getPhoneNumber()))
		{
			log.error("Invalid Dependent Phone Number provided");
			return false;
		}
						
		newDependent.setName(newDependent.getName().trim());
		newDependent.setPhoneNumber(newDependent.getPhoneNumber().trim().length()==0
				?null:newDependent.getPhoneNumber());
		
		return true;
	}
}
