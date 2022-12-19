package com.fnp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fnp.dto.Student;
import com.fnp.exception.StudentBindingException;
import com.fnp.model.StudentModel;
import com.fnp.service.StudentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StudentController {

	private static Logger log = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	StudentService studentService;
	
	@ApiOperation(value = "To Post specified student data to database.", notes = "Post Student API.")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully Added to database."),
	  @ApiResponse(code = 404, message = "Student data could'nt be added.")
	})
	@PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentModel studentModel,
			BindingResult bindingResult) throws Exception {
		Student studentObj = null;
		if (bindingResult.hasErrors()) {

			throw new StudentBindingException(bindingResult.toString());
		}
		studentObj = studentService.saveStudent(studentModel);
		return new ResponseEntity<>(studentObj, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "To get delete student data from database.", notes = "Delete Student API.")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully deleted."),
	  @ApiResponse(code = 404, message = "Student could not be deleted.")
	})
	@DeleteMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int delete) {
		String status = studentService.deleteStudent(delete);
		return new ResponseEntity<String>(status, HttpStatus.GONE);
	}
	@ApiOperation(value = "To get specified student data from database.", notes = "Get Student API.")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved."),
	  @ApiResponse(code = 404, message = "Student not found.")
	})
	@GetMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		Student studentObj = studentService.getStudent(id);
		return new ResponseEntity<>(studentObj, HttpStatus.OK);
	}
	
	@ApiOperation(value = "To update specific student data.", notes = "Update student data.")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved."),
	  @ApiResponse(code = 404, message = "Student couldnt be updated.")
	})
	@PutMapping("/students")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateStudent(@RequestBody StudentModel studentModel) {
		String status = studentService.updateStudent(studentModel);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
	@ApiOperation(value = "To get specified student data from database.", notes = "Get Student API.")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved."),
	  @ApiResponse(code = 404, message = "Student not found.")
	})
	@GetMapping("/students")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Student>> getallStudent() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
}
