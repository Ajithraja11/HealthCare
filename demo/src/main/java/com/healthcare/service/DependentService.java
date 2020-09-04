package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Dependent;
import com.healthcare.repository.DependentDaoImpl;
import com.healthcare.repository.EnrolleeDaoImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DependentService {

	@Autowired
	private DependentDaoImpl newDependentDaoImpl;
	@Autowired 
	private EnrolleeDaoImpl newEnrolleeDaoImpl;
	
	

	public List<ResponseEntity<?>> createNewDependent(Dependent[] newDependent)
	{
		log.info("Inside service createNewDependent");
		List<ResponseEntity<?>> list=new ArrayList<>();
		for(Dependent tempDependent:newDependent)
		{
			if(validateDependent(tempDependent) && newEnrolleeDaoImpl.getEnrollee(tempDependent.getEnrollee_id())!=false)
			{
				try {
				list.add( newDependentDaoImpl.createNewDependent(tempDependent));
				log.info("Dependent added successfully");
				}
				catch(Exception e) {
					list.add( new ResponseEntity<Object>("Exception occured while adding into database",HttpStatus.BAD_REQUEST));
					log.error("Exception occured while adding dependent to the database");
				}
			}
			else
				{
					list.add( new ResponseEntity<Object>("Invalid data provided",HttpStatus.BAD_REQUEST));
					log.info("Invalid dependent data provided");
				}
		}
		return list;
		
	}
	
	public ResponseEntity<?> updateDependent(Dependent newDependent)
	{
		log.info("Inside service updateDependent");
		if(validateDependent(newDependent)&& newEnrolleeDaoImpl.getEnrollee(newDependent.getEnrollee_id())!=false)
			{
				try {
					return newDependentDaoImpl.updateDependent(newDependent);
					
				}
				catch(Exception e)
				{
					log.error("Exception occured while adding dependent to the database");
					return new ResponseEntity<Object>("Exception occured while adding into database",HttpStatus.BAD_REQUEST);
				}
			}
		else
		{
			log.info("Invalid dependent data provided");
			return new ResponseEntity<Object>("Invalid dependent data provided",HttpStatus.BAD_REQUEST);
			
		}
	}
	
	public ResponseEntity<?> deleteDependent( int enrolleeID, int dependentID)
	{
		log.info("Inside service deleteDependent");
		if(newEnrolleeDaoImpl.getEnrollee(enrolleeID)!=false)
		{
			try {
			return newDependentDaoImpl.deleteDependent(enrolleeID, dependentID);
			}
			catch(Exception e)
			{
				log.error("Exception occured while adding dependent to the database");
				return new ResponseEntity<Object>("Exception occured while deleting",HttpStatus.BAD_REQUEST);
			}
		}
		else
		{
			log.info("EnrolleeId not found");
			return new ResponseEntity<Object>("EnrolleeId not found",HttpStatus.BAD_REQUEST);
		}
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
