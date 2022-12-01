package com.fnp.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fnp.dao.StudentRepository;
import com.fnp.dto.Student;
import com.fnp.dto.StudentModel;

@Repository
public class StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	Student student;
	
	public Student saveStudent(StudentModel studentModel) {
		Date date = new Date();
		student.setId(studentModel.getId());
		student.setName(studentModel.getName());
		student.setPhone(studentModel.getPhone());
		student.setEmail(studentModel.getEmail());
		student.setCreatedby("John");
		student.setUpdatedBy("John");
		student.setCreatedOn(date);
		student.setLastUpdatedBy(date);
		return repository.save(student);
	}

	public String deleteStudent(int id) {

		if (repository.existsById(id)) {
			repository.deleteById(id);
			return "deleted";
		} else {
			return "doesNotExist";
		}
	}

	public String updateStudent(StudentModel studentModel) {
		
		Date date = new Date();

		student.setId(studentModel.getId());
		student.setName(studentModel.getName());
		student.setPhone(studentModel.getPhone());
		student.setEmail(studentModel.getEmail());
		student.setUpdatedBy("John");
		student.setLastUpdatedBy(date);
		
		if (repository.existsById(student.getId())) {
			repository.save(student);
			return "Updated Successfully.";
		}
		return "Record does not exist.";
	}

	public Optional<Student> getStudent(int id) {

		if (repository.existsById(id)) {

			Optional<Student> obj = repository.findById(id);
			return obj;
		}
		return null;
	}

	public List<Student> getAllStudents() {

		List<Student> list = repository.findAll();
		return list;
	}
}
