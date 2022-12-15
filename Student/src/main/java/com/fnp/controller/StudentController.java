package com.fnp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.fnp.dto.StudentModel;
import com.fnp.exception.StudentBindingException;
import com.fnp.service.StudentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping(value = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Student> addStudent(@Valid @RequestBody StudentModel studentModel,
			BindingResult bindingResult) throws Exception {
		Student studentObj = null;
		if (bindingResult.hasErrors()) {

			throw new StudentBindingException(bindingResult.toString());
		}
		studentObj = studentService.saveStudent(studentModel);
		return ResponseEntity.ok().body(studentObj);
	}

	@DeleteMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int delete) {
		String status = studentService.deleteStudent(delete);
		return ResponseEntity.ok(status);
	}
	
	@ApiOperation(value = "Get a product by id", notes = "Returns a product as per the id")
	@ApiResponses(value = {
	  @ApiResponse(code = 200, message = "Successfully retrieved"),
	  @ApiResponse(code = 404, message = "Not found - The product was not found")
	})
	@GetMapping("/students/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<Optional<Student>> getStudent(@PathVariable("id") int id) {
		Optional<Student> studentObj = studentService.getStudent(id);
		return ResponseEntity.ok(studentObj);
	}

	@PutMapping("/students")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateStudent(@RequestBody StudentModel studentModel) {
		String status = studentService.updateStudent(studentModel);
		return ResponseEntity.ok(status);
	}

	@GetMapping("/students")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<Student>> getallStudent() {
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
}
