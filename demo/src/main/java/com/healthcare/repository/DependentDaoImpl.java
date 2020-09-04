package com.healthcare.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<?> createNewDependent(Dependent newDependent)
	{
		newDependent.setId(0);
		try {
			Optional<Enrollee> enrollee=newEnrolleDao.findById(newDependent.getEnrollee_id());
			
			if(enrollee.isPresent())
			{
				enrollee.get().getDependents().add(newDependent);
				newDependentDao.save(newDependent);
				log.info("New dependent added for the enrolleeID "+enrollee.get().getId());
			}
			else
				{
				log.error("Enrollee ID is not present in the database");
				throw new Exception();
				
				}
		}
		catch(Exception e) {
			log.error("Exception "+e.getStackTrace());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> updateDependent(Dependent newDependent){
		try {
		newDependentDao.save(newDependent);
		log.info("New Dependent "+newDependent.getId()+" is updated");
		}
		catch (Exception e){
			log.error("Exception "+e.getStackTrace());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> deleteDependent( int enrolleeID, int dependentID){
		Optional<Enrollee> enrollee=newEnrolleDao.findById(enrolleeID);
		try {
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
			throw new Exception();
		}
		}
		catch(Exception e) {
			log.error("Exception "+e.getStackTrace());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
}
