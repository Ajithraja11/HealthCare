package com.healthcare.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Enrollee;
import com.healthcare.repository.EnrolleeDaoImpl;
import com.healthcare.validator.Validator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnrolleeService {

	@Autowired
	private EnrolleeDaoImpl newEnrolleeDaoImpl;
	
	Validator validator;
	
	public List<ResponseEntity<?>> createEnrollee(Enrollee[] newEnrolle)
	{
		List<ResponseEntity<?>> list=new ArrayList<>();
		for(Enrollee enrollee:newEnrolle)	
		{
			if(enrolleeValidate(enrollee))
				{
				try {
					list.add( newEnrolleeDaoImpl.createEnrollee(enrollee));
					}
				catch (Exception e) {
					log.error("Failed to insert into database ",e);
					list.add( new ResponseEntity<Object>(HttpStatus.BAD_REQUEST));
				}
				
				}
			else
				{
				list.add( new ResponseEntity<Object>(HttpStatus.BAD_REQUEST));
				log.info("Invalid data provided");
				}
		}
		return list;
		
	}
	
	public ResponseEntity<?> updateEnrollee( Enrollee theEnrolle)
	{
		
		if(enrolleeValidate(theEnrolle) && newEnrolleeDaoImpl.getEnrollee(theEnrolle.getId()))
			{
			try {
				return newEnrolleeDaoImpl.updateEnrollee(theEnrolle);
			}
			catch (Exception e)
			{
				log.error("Failed to insert into database ",e);
				return new ResponseEntity<Object>("Exception while updating data into database",HttpStatus.BAD_REQUEST);
			}
			}
		else
			{
			log.info("Invalid EnrolleeID provided");
			return new ResponseEntity<Object>("Invalid EnrolleeID provided",HttpStatus.BAD_REQUEST);
			
			}
	}
	
	public ResponseEntity<?> deleteEnrollee( int enrolleeId) 
	{
		if(newEnrolleeDaoImpl.getEnrollee(enrolleeId))
		{
			try {
				return newEnrolleeDaoImpl.deleteEnrollee(enrolleeId);
			}
			catch(Exception e) {
				log.error("Failed to delete the data in the database "+e);
				return new ResponseEntity<Object>("Exception while deleting data in the database",HttpStatus.BAD_REQUEST);
			}
		}
		else {
			log.info("Invalid EnrolleeID provided");
			return new ResponseEntity<Object>("Invalid EnrolleeID provided",HttpStatus.BAD_REQUEST);
			
		}
	}
	
	public boolean enrolleeValidate(Enrollee theEnrolle)
	{
		if(validator.checkName(theEnrolle.getName()))
		{
			log.error("Invalid Enrollee Name provided");
			return false;
		}	
		if(theEnrolle.getStatus()==null)  
		{
			log.error("Invalid Enrollee Statusr provided");
			return false;
		}
		if(validator.checkBirthDate(theEnrolle.getBirthDate()))//validate birthdate
		{
			log.error("Invalid Enrollee Birthdate provided");
			return false;
		}	
		if(validator.checkPhoneNumber(theEnrolle.getPhoneNumber()))
		{
			log.error("Invalid Enrollee Phone Number provided");
			return false;
		}
			
		theEnrolle.setName(theEnrolle.getName().trim());
		theEnrolle.setPhoneNumber(theEnrolle.getPhoneNumber().trim().length()==0
									?null:theEnrolle.getPhoneNumber());
		
		return true;
	}
}



















