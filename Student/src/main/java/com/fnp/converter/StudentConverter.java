package com.fnp.converter;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fnp.dto.Student;
import com.fnp.dto.StudentModel;

@Component
public class StudentConverter {
	
	public Student saveConverter(StudentModel studentModel) {
		
		Student student = new Student();
		Date date = new Date();
		student.setId(studentModel.getId());
		student.setName(studentModel.getName());
		student.setPhone(studentModel.getPhone());
		student.setEmail(studentModel.getEmail());
		student.setCreatedby("John");
		student.setUpdatedBy("John");
		student.setCreatedOn(date);
		student.setLastUpdatedBy(date);
		
		return student;
		
	}
	
	public Student updateConverter(StudentModel studentModel) {
		
		Student student = new Student();
		Date date = new Date();

		student.setId(studentModel.getId());
		student.setName(studentModel.getName());
		student.setPhone(studentModel.getPhone());
		student.setEmail(studentModel.getEmail());
		student.setUpdatedBy("John");
		student.setLastUpdatedBy(date);
		
		return student;
		
	}
}
