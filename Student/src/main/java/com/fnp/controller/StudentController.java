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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fnp.dto.Student;
import com.fnp.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping(value = "/students" /* , produces= {"application/json"} */)
	public Student sendStudent(@ModelAttribute Student student) {

		Student stu = studentService.saveStudent(student);
		System.out.println(stu);
		System.out.println(stu.getCreatedby());
		return stu;
	}

	@DeleteMapping("/students/{id}")
	public String deleteStudent(@RequestParam int delete) {

		String status = studentService.deleteStudent(delete);
		return status;
	}

	@GetMapping("/students/{id}")
	public Optional<Student> readStudent(@PathVariable("id") int id) {

		Optional<Student> s = studentService.readStudent(id);
		return s;
	}

	@PutMapping("/students")
	public String updateStudent(Student s) {

		String ss = studentService.updateStudent(s);
		return ss;
	}

	@PostMapping("/students/getall")
	public List<Student> getallStudent() {

		List<Student> list = studentService.getAllStudents();
		return list;
	}

}
