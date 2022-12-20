package com.fnp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fnp.dto.Student;
import com.fnp.model.StudentModel;
import com.fnp.repository.StudentRepository;

@SpringBootTest
public class StudentServiceTest {
	
	@Mock
	private StudentRepository repo;
	
	@MockBean
	private StudentService service;
	
	static Student s;
	static Student s1;
	static Student s2;
	static Date date;
	
	@BeforeAll 
	static void testCreateStudent() {

		date = new Date();

		s = new Student(14343435,"tar","jkhj@.com",65765675,date,date,"John","John");
		s1 = new Student(14343436,"tar","jkhj@.com",65765675,date,date,"John","John");
		s2 = new Student(14343437,"tar","jkhj@.com",65765675,date,date,"John","John");
	}

	@Test
	void saveStudent() {
		
		StudentModel studentModel = new StudentModel();
		studentModel.setId(1);
		studentModel.setEmail("hgg@hjg.com");
		studentModel.setName("dgtrdt");
		studentModel.setPhone(7676768);
		
		
		Date date = new Date();
		Student student = new Student();
		student.setId(1);
		student.setName("dgtrdt");
		student.setPhone(65765675);
		student.setEmail("hgg@hjg.com");
		student.setCreatedby("John");
		student.setUpdatedBy("John");
		student.setCreatedOn(date);
		student.setLastUpdatedBy(date);
	
		Student student2 = service.saveStudent(studentModel);
		assertEquals(1434343473,student2.getId());

	}
	
	@Test
	void testGetAllStudents() {
		
		Date date = new Date();
		//When method requires an argument which has to be a Mock(method call on mock);
		when(repo.findAll()).thenReturn(Stream.of(new Student(14343435,"tar","jkhj@.com",65765675,date,date,"John","John")).collect(Collectors.toList()));
		assertEquals(46,service.getAllStudents().size());
	}
	
	@Test
	void testDeleteStudent() {
		when(repo.save(s)).thenReturn(s);
		repo.delete(s);
		verify(repo, times(1)).delete(s);
	}
	@Test
	void testGetStudent() {
		
		when(repo.save(s)).thenReturn(s);
		when(repo.findById(s.getId())).thenReturn(Optional.of(s));
		assertEquals(repo.findById(s.getId()), Optional.of(s));
	}
}
