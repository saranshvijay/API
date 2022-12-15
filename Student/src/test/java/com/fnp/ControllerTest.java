//package com.fnp;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fnp.controller.StudentController;
//import com.fnp.dto.Student;
//import com.fnp.service.StudentService;
//
////@SpringBootTest
//
//@WebMvcTest(StudentController.class)
//public class ControllerTest {
//	
//	@Autowired
//	private MockMvc mockmvc;
//	
////	@Autowired
////	StudentModel studentModel;
//	
//	@Autowired
//	private StudentService service;
//	
////	@Autowired
////	private Student student;
////	
//////	@Test
//////	public void testSaveStudent() {
//////		when(service.saveStudent(studentModel)).thenReturn(student);
//////		this.mockmvc.perform(post("/students")).andDo()
////////	this.mockmvc.perform(get(null))
//////	}
////	
////	@Test
////	public void createStudentAPI() throws Exception{
////		this.mockmvc.perform(MockMvcRequestBuilders
////	  			.post("/students")
////	  			.accept(MediaType.APPLICATION_JSON))
////	      .andDo(print())
////	      .andExpect(status().isOk())
////	      .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
////	      .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());
////	}	
////	
////	
////	  public static String asJsonString(final Object obj) {
////		    try {
////		      return new ObjectMapper().writeValueAsString(obj);
////		    } catch (Exception e) {
////		      throw new RuntimeException(e);
////		    }
////		  }
//
//	@Test
//	public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
//	  throws Exception {
//	    
//	    Student alex = new Student();
//	    alex.setId(2);
//
//	    List<Student> allEmployees = Arrays.asList(alex);
//
//	    given(service.getAllStudents()).willReturn(allEmployees);
//
//	    mockmvc.perform(get("/students")
//	      .contentType(MediaType.APPLICATION_JSON))
//	      .andExpect(status().isOk())
////	      .andExpect(jsonPath("$", hasSize(1)))
//	      .andExpect(jsonPath("$[1].name", is(alex.getName())));
//	}
//	
//	
//}
