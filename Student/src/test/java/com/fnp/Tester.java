package com.fnp;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.fnp.dao.StudentRepository;
import com.fnp.dto.Student;

@SpringBootTest
public class Tester {
	
	@Mock
	private StudentRepository repo;
	
	
	@Test 
	void isdjhe() {
		Date date = new Date();
		Student student = new Student();
		student.setId(22);
		student.setName("studentModel.getName()");
		student.setPhone(65765675);
		student.setEmail("jkhj@.com");
		student.setCreatedby("John");
		student.setUpdatedBy("John");
		student.setCreatedOn(date);
		student.setLastUpdatedBy(date);
		
		repo.save(student);
		Boolean res = repo.existsById(23);
		assertThat(res).isTrue();
	}
	
	@Test
	void kjh() {
		
		
	}
		
}
