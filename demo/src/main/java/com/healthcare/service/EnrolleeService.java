package com.healthcare.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.healthcare.entity.Enrollee;
import com.healthcare.repository.EnrolleeDaoImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnrolleeService {

	@Autowired
	private EnrolleeDaoImpl newEnrolleeDaoImpl; 
	
	public ResponseEntity<?> createEnrollee(Enrollee newEnrolle)
	{
		if(enrolleeValidate(newEnrolle))
			return newEnrolleeDaoImpl.createEnrollee(newEnrolle);
		else
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> updateEnrollee( Enrollee theEnrolle)
	{
		if(enrolleeValidate(theEnrolle))
			return newEnrolleeDaoImpl.updateEnrollee(theEnrolle);
		else
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> deleteEnrollee( int enrolleeId) 
	{
		return newEnrolleeDaoImpl.deleteEnrollee(enrolleeId);
	}
	
	public boolean enrolleeValidate(Enrollee theEnrolle)
	{
		if(theEnrolle.getName()==null || theEnrolle.getName().trim().length()==0)
		{
			log.error("Invalid Enrollee Name provided");
			return false;
		}	
		if(theEnrolle.getStatus()==null)  
		{
			log.error("Invalid Enrollee Statusr provided");
			return false;
		}
		if(theEnrolle.getBirthDate()==null)
		{
			log.error("Invalid Enrollee Birthdate provided");
			return false;
		}	
		if(Pattern.matches(".*[a-zA-Z&%^$@!].*",theEnrolle.getPhoneNumber()))
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



















