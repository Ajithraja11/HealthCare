package com.healthcare.repository;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.healthcare.Dao.EnrolleeDao;
import com.healthcare.entity.Enrollee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EnrolleeDaoImpl {

	@Autowired
	private EnrolleeDao newEnrolleDao;
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> createEnrollee(Enrollee newEnrolle) throws Exception{
		
		log.info("Inside repository createEnrollee");
		newEnrolle.setId(0);	
		newEnrolleDao.save(newEnrolle);
		log.info("New Enrollee of name "+newEnrolle.getName() + " is added to the database");
		return new ResponseEntity<Object>("Enrollee created",HttpStatus.CREATED);
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> updateEnrollee( Enrollee theEnrolle) throws Exception
	{
		log.info("Inside repository updateEnrollee");
		newEnrolleDao.save(theEnrolle);
		log.info("Enrollee data is updated for the enrolleeID "+theEnrolle.getId());
		return new ResponseEntity<Object>("Enrolle updated for the ID "+theEnrolle.getId(),HttpStatus.ACCEPTED);
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> deleteEnrollee( int enrolleeId) throws Exception
	{
		log.info("Inside repository deleteEnrollee");
		newEnrolleDao.deleteById(enrolleeId);
		log.info("Enrollee data is deleted for the enrolleeID "+enrolleeId);
		return new ResponseEntity<Object>("Enrolle deleted successfully",HttpStatus.ACCEPTED);
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public boolean getEnrollee(int enrolleeID) 
	{	
		if(newEnrolleDao.findById(enrolleeID).isPresent())
		{
			return true;
		}
		log.info("EnrolleID "+enrolleeID + " is invalid");
		return false;
	}
	
}
