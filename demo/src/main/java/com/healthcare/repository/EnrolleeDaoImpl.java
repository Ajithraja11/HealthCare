package com.healthcare.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.healthcare.Dao.EnrolleeDao;
import com.healthcare.entity.Enrollee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EnrolleeDaoImpl {

	@Autowired
	private EnrolleeDao newEnrolleDao;
	
	public ResponseEntity<?> createEnrollee(Enrollee newEnrolle){
		newEnrolle.setId(0);
		try {
		newEnrolleDao.save(newEnrolle);
		log.info("New Enrollee of name "+newEnrolle.getName() + " is added to the database");
		}
		catch(Exception e) {
			log.error("Exception "+e.getStackTrace());
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	public ResponseEntity<?> updateEnrollee( Enrollee theEnrolle)
	{
		try {
			newEnrolleDao.save(theEnrolle);
			log.info("Enrollee data is updated for the enrolleeID "+theEnrolle.getId());
			}
			catch(Exception e) {
				log.error("Exception "+e.getStackTrace());
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<?> deleteEnrollee( int enrolleeId) {
		try {
			newEnrolleDao.deleteById(enrolleeId);
			log.info("Enrollee data is deleted for the enrolleeID "+enrolleeId);
		}
		catch(Exception e) {
			log.error("Exception "+e.getStackTrace());
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(HttpStatus.ACCEPTED);
	}
	
}
