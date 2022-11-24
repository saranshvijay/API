package com.fnp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fnp.dao.StudentDao;
import com.fnp.dto.Student;
import com.fnp.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/save")
	public Model sendStudent(Student student, Model model) {
		
		Student s = studentService.saveStudent(student);
		
		model.addAttribute("studentId", s.getId());
		model.addAttribute("studentName", s.getName());
		model.addAttribute("studentEmail", s.getEmail());
		model.addAttribute("studentCreator", s.getCreatedby());
		model.addAttribute("studentUpdater", s.getUpdatedBy());
		model.addAttribute("studentPhone", s.getPhone());
		model.addAttribute("studentCreationDate", s.getCreatedOn());
		model.addAttribute("studentUpdationDate", s.getLastUpdatedBy());

		System.out.println(s);
		System.out.println(s.getCreatedby());
		return model;
	}
	
	@PostMapping("/delete")
	public Model deleteStudent(@RequestParam int delete, Model model) {
			
		String status = studentService.deleteStudent(delete);
		model.addAttribute("msg",status);
		return model;
	}
	
	@PostMapping("/read")
	public Model readStudent(@RequestParam int id, Model model) {
			
		Optional<Student> s = studentService.readStudent(id);
		model.addAttribute("msg",s);
		return model;
	}
	
	@PostMapping("/update")
	public Model updateStudent(Student s, Model model) {
		String ss = studentService.updateStudent(s);
		model.addAttribute("status",ss);
		return model;
	}
	
	@PostMapping("/getall")
	public Model getallStudent(Model model) throws JsonProcessingException {
		String s = studentService.getAllStudents().toString();
		//List<Student> list = studentService.getAllStudents();
		model.addAttribute("status",s);
		
		//ObjectMapper map = new ObjectMapper();
		//String json = map.writeValueAsString(list);
		return model;
	}

}
