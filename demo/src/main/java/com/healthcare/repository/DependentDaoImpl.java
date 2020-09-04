package com.healthcare.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;

import com.healthcare.Dao.DependentDao;
import com.healthcare.Dao.EnrolleeDao;
import com.healthcare.entity.Dependent;
import com.healthcare.entity.Enrollee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DependentDaoImpl {

	@Autowired
	private EnrolleeDao newEnrolleDao;
	@Autowired
	private DependentDao newDependentDao;
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> createNewDependent(Dependent newDependent) throws Exception
	{
		log.info("Inside Repository createNewDependent");
		newDependent.setId(0);
		Optional<Enrollee> enrollee=getEnrollee(newDependent.getEnrollee_id());
			
		if(enrollee.isPresent())
		{
			enrollee.get().getDependents().add(newDependent);
			newDependentDao.save(newDependent);
			log.info("New dependent added for the enrolleeID "+enrollee.get().getId());
			return new ResponseEntity<Object>("New Dependent added for enrolleID "+enrollee.get().getId(),HttpStatus.CREATED);
		}
		else
		{
			log.error("Enrollee ID is not present in the database");
			return new ResponseEntity<Object>("EnrolleeId not found",HttpStatus.NOT_FOUND);		
		}	
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> updateDependent(Dependent newDependent) throws Exception
	{
		log.info("Inside Repository updateDependent");
		newDependentDao.save(newDependent);
		log.info("New Dependent "+newDependent.getId()+" is updated");
		return new ResponseEntity<Object>("DependentID "+newDependent.getId()+ "updated",HttpStatus.ACCEPTED);
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public ResponseEntity<?> deleteDependent( int enrolleeID, int dependentID) throws Exception{
		
		log.info("Inside Repository deleteDependent");
		
		Optional<Enrollee> enrollee=getEnrollee(enrolleeID);
		
		if(enrollee.isPresent())
		{
			List<Dependent> dependents=enrollee.get().getDependents();
			dependents=dependents.parallelStream().filter(dependent->dependent.getId()!=dependentID).collect(Collectors.toList());
			enrollee.get().setDependents(dependents);
			newDependentDao.deleteById(dependentID);
			newEnrolleDao.save(enrollee.get());
			
			log.info("DependentID "+dependentID+" is deleted for the enrolleID "+enrolleeID);
		}
		else {
			log.error("EnrolleID "+enrolleeID +" is not available in database");
			return new ResponseEntity<Object>("EnrolleeId not found",HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>("DependentID"+dependentID+ "deleted for enrolleID "+enrolleeID,HttpStatus.ACCEPTED);
	}
	
	@Retryable(value = { SQLException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public Optional<Enrollee> getEnrollee(int enrolleeID)
	{
		log.info("Inside Get Enrollee");
		return newEnrolleDao.findById(enrolleeID);
	}
	
}
