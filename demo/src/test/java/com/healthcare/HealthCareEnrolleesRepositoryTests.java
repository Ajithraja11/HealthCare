package com.healthcare;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.healthcare.entity.Dependent;
import com.healthcare.entity.Enrollee;
import com.healthcare.repository.DependentDaoImpl;
import com.healthcare.repository.EnrolleeDaoImpl;


@SpringBootTest
@DisplayName("Repository Test")
@RunWith(MockitoJUnitRunner.class)
class HealthCareEnrolleesRepositoryTests {
	
	@DisplayName("Enrollee Repository Test")
	@Nested
	
	class EnrolleeRepositoryTest
	{
		@Mock
		EnrolleeDaoImpl enrolleeRepMock;
		@SuppressWarnings("deprecation")
		@Test
		public void createEnrolleeRepositoryTest() 
		{
			Enrollee newEnrollee=new Enrollee("asdasd",true,new Date(12,12,2020));
		
		try {
			Mockito.doReturn(new ResponseEntity<Object>("Enrollee created",HttpStatus.CREATED)).
			when(enrolleeRepMock).createEnrollee(newEnrollee);
		
			assertEquals((new ResponseEntity<Object>("Enrollee created",HttpStatus.CREATED)),
					(enrolleeRepMock.createEnrollee(newEnrollee)));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void updateEnrolleeRepositoryTest() {
			Enrollee enrollee=new Enrollee(1,"asdasd",true,new Date(12,12,2020));
			try {
				Mockito.doReturn(new ResponseEntity<Object>("Enrollee updated",HttpStatus.ACCEPTED)).
				when(enrolleeRepMock).updateEnrollee(enrollee);
			
				assertEquals((new ResponseEntity<Object>("Enrollee updated",HttpStatus.ACCEPTED)),
						(enrolleeRepMock.updateEnrollee(enrollee)));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void deleteEnrolleeRepositoryTest()
		{
			try {
				Mockito.doReturn(new ResponseEntity<Object>("Enrollee deleted",HttpStatus.ACCEPTED)).
				when(enrolleeRepMock).deleteEnrollee(2);
			
				assertEquals((new ResponseEntity<Object>("Enrollee created",HttpStatus.ACCEPTED)).getStatusCode(),
						(enrolleeRepMock.deleteEnrollee(2).getStatusCode()));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void getEnrolleeImplementationTest()
		{
			try {
				Mockito.doReturn(true).
				when(enrolleeRepMock).getEnrollee(2);
			
				assertEquals(true,
						(enrolleeRepMock.getEnrollee(2)));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	@Nested
	@DisplayName("Dependent Repository Test")
	class DependentRepositoryTest{
		DependentDaoImpl dependentRepMock=mock(DependentDaoImpl.class);
		
		@Test
		public void createDependentRepositoryTest()
		{
			Dependent newDependent=new Dependent("asdasd",new Date(12,12,2020),1);
			try {
				Mockito.doReturn(new ResponseEntity<Object>("Enrollee created",HttpStatus.CREATED)).
				when(dependentRepMock).createNewDependent(newDependent);
			
				assertEquals((new ResponseEntity<Object>("Enrollee created",HttpStatus.CREATED)),
						(dependentRepMock.createNewDependent(newDependent)));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void updateDependentRepositoryTest()
		{
			Dependent dependent=new Dependent(1,"asdasd",new Date(12,12,2020),1);
			try {
				Mockito.doReturn(new ResponseEntity<Object>("Enrollee updated",HttpStatus.ACCEPTED)).
				when(dependentRepMock).updateDependent(dependent);
			
				assertEquals((new ResponseEntity<Object>("Enrollee updated",HttpStatus.ACCEPTED)),
						(dependentRepMock.updateDependent(dependent)));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Test
		public void deleteDependentRepositoryTest()
		{
			try {
				Mockito.doReturn(new ResponseEntity<Object>("Enrollee deleted",HttpStatus.ACCEPTED)).
				when(dependentRepMock).deleteDependent(1,1);
			
				assertEquals((new ResponseEntity<Object>("Enrollee deleted",HttpStatus.ACCEPTED)).getStatusCode(),
						(dependentRepMock.deleteDependent(1,1).getStatusCode()));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
