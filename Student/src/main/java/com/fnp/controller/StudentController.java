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

@RestController
public class StudentController {

	private static Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	StudentService studentService;

	@PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentModel studentModel,
			BindingResult bindingResult) throws Exception {
		Student studentObj = null;
		if (bindingResult.hasErrors()) {

			throw new StudentBindingException(bindingResult.toString());
		}
		studentObj = studentService.saveStudent(studentModel);
		return new ResponseEntity<>(studentObj,HttpStatus.CREATED);
	}

	@DeleteMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int delete) {
		String status = studentService.deleteStudent(delete);
		return new ResponseEntity<String>(status, HttpStatus.GONE);
	}
	

	@GetMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
		Student studentObj = studentService.getStudent(id);
		return new ResponseEntity<>(studentObj,HttpStatus.OK);
	}

	@PutMapping("/students")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateStudent(@RequestBody StudentModel studentModel) {
		String status = studentService.updateStudent(studentModel);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}

	@GetMapping("/students")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Student>> getallStudent() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
}
