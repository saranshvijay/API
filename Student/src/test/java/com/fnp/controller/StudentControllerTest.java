package com.fnp.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnp.controller.StudentController;
import com.fnp.dto.Student;
import com.fnp.model.StudentModel;
import com.fnp.repository.StudentRepository;
import com.fnp.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc //need this in Spring Boot test
public class StudentControllerTest {
	
	@InjectMocks
	private StudentController studentController;

	@Mock
	private StudentService studentService;
	
	@Mock
	StudentRepository repo;
	
	@Autowired
	private MockMvc mvcc;

	static Student s;
	static Student s1;
	static Student s2;
	static StudentModel studentModel;
	static Date date;

	@BeforeAll
	static void testCreateStudentData() {

		date = new Date();

		s = new Student(14343435, "tar", "jkhj@.com", 65765675, date, date, "John", "John");
		s1 = new Student(14343436, "tar", "jkhj@.com", 65765675, date, date, "John", "John");
		s2 = new Student(14343437, "tar", "jkhj@.com", 65765675, date, date, "John", "John");
		
		studentModel = new StudentModel();
		studentModel.setId(1);
		studentModel.setEmail("hgg@hjg.com");
		studentModel.setName("dgtrdt");
		studentModel.setPhone(7676768);
		
		
	}
	
	public static String asJsonString(final Object obj) {
		try {
			String a = new ObjectMapper().writeValueAsString(obj);
			System.out.println(a);
			return a;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	 @Test
	 void testaddStudent() {
		 
		 Mockito.when(studentService.saveStudent(studentModel)).thenReturn(s1);
		 ResponseEntity<Student> responseEntity = studentController.addStudent(studentModel);
		 Assertions.assertNotNull(responseEntity.getBody().getEmail());
		 assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
	 }
	 
	 @Test
	 void testDeleteStudent() {
		 
		 Mockito.when(studentService.deleteStudent(1)).thenReturn("Deleted");
		 ResponseEntity<String> responseEntity = studentController.deleteStudent(1);
		 Assertions.assertEquals("Deleted", responseEntity.getBody());
	 }
	 
	 @Test
	 void testUpdateStudent() {
		
		 Mockito.when(studentService.updateStudent(studentModel)).thenReturn("Updated Successfully.");
		 ResponseEntity<String> responseEntity = studentController.updateStudent(studentModel);
		 Assertions.assertEquals("Updated Successfully.", responseEntity.getBody());

	 }
	 
	 @Test
	 void testGetStudent() {
		 
		 Mockito.when(studentService.getStudent(14343435)).thenReturn(s);
		 ResponseEntity<Student> responseEntity = studentController.getStudent(14343435);
		 Assertions.assertEquals(14343435, responseEntity.getBody().getId());
	 }
	 
	 @Test
	 void testGetAllStudent() {
		 
		 List<Student> list = new ArrayList<>();
		 list.add(s);
		 list.add(s1);
		 Mockito.when(studentService.getAllStudents()).thenReturn(list);
		 ResponseEntity<List<Student>> responseEntity = studentController.getallStudent();
		 Assertions.assertEquals(2, responseEntity.getBody().size());
		 
	 }
	 
	 @WithMockUser(roles = "ADMIN")
	 @Test
	 public void testAddStudentController() throws Exception {

		 String smodel = asJsonString(studentModel);
	     mvcc.perform(MockMvcRequestBuilders.post("/students")
	       .content(smodel)
	       .contentType(MediaType.APPLICATION_JSON_VALUE))
	       .andExpect(MockMvcResultMatchers.status().isCreated())
	       .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
	 }
	 
	 @WithMockUser(roles = "ADMIN")
	 @Test
	 public void testDeleteStudentController() throws Exception {
		 
		 mvcc.perform(MockMvcRequestBuilders.delete("/students/1")).andExpect(MockMvcResultMatchers.status().isGone())
		 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")));
	 }
	 
	 @WithMockUser(roles = "ADMIN")
	 @Test
	 public void testUpdateStudentController() throws Exception {
		 
		 String student = asJsonString(studentModel);
		 mvcc.perform(MockMvcRequestBuilders.put("/students").content(student)
				 .contentType(MediaType.APPLICATION_JSON_VALUE))
		 .andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content()
				 .contentType(MediaType.valueOf("text/plain;charset=UTF-8")));
				
	 }
	 
	 @WithMockUser(roles = "USER")
	 @Test
	 public void testGetStudentController() throws Exception {
		 
		 mvcc.perform(MockMvcRequestBuilders.get("/students/1").contentType(MediaType.APPLICATION_JSON_VALUE))
		 .andExpect(MockMvcResultMatchers.status().isOk());
	 }
	 
	 @WithMockUser(roles = "USER")
	 @Test
	 public void testGetAllStudentsController() throws Exception {
		 
	 mvcc.perform(MockMvcRequestBuilders.get("/students")).andExpect(MockMvcResultMatchers.status().isOk())
	 .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
	 
	 }
}
	 

