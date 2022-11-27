package com.fnp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@PostMapping("/students")
	public Student sendStudent(@ModelAttribute Student student) {
		Student studentObj = studentService.saveStudent(student);
		return studentObj;
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
	public String updateStudent(@RequestBody Student student) {
		String status = studentService.updateStudent(student);
		return status;
	}

	@GetMapping("/students")
	public List<Student> getallStudent() {
		List<Student> students = studentService.getAllStudents();
		return students;
	}
}
