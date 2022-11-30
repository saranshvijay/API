package com.fnp.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fnp.dto.Student;
import com.fnp.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping(value = "/students",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> sendStudent(@Valid @RequestBody Student student, BindingResult bindingResult) {
		System.out.println(bindingResult+"11111111111111");
		//System.out.println(student);
		Student studentObj= null;
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = (Validator)factory.getValidator();
//		Set<ConstraintViolation<Student>> violations = validator.validate(student);
//		System.out.println(violations.toString());
		if(bindingResult.hasErrors()) {
			System.out.println(bindingResult.getErrorCount() +"\n" +bindingResult.getFieldErrorCount("name"));
			return ResponseEntity.badRequest().body(student);
		}
		studentObj = studentService.saveStudent(student);
		return ResponseEntity.ok(studentObj);
	}

	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable("id") int delete) {
		String status = studentService.deleteStudent(delete);
		return status;
	}

	@GetMapping("/students/{id}")
	public Optional<Student> getStudent(@PathVariable("id") int id) {
		Optional<Student> studentObj = studentService.getStudent(id);
		return studentObj;
	}

	@PutMapping("/students")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		String status = studentService.updateStudent(student);
		return ResponseEntity.ok(status);
	}

	@GetMapping("/students")
	public List<Student> getallStudent() {
		List<Student> students = studentService.getAllStudents();
		return students;
	}
}
