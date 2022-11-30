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
	Student s;
	
	public Student saveStudent(StudentModel studentModel) {
		Date date = new Date();
		s.setId(studentModel.getId());
		s.setName(studentModel.getName());
		s.setPhone(studentModel.getPhone());
		s.setEmail(studentModel.getEmail());
		s.setCreatedby("John");
		s.setUpdatedBy("John");
		s.setCreatedOn(date);
		s.setLastUpdatedBy(date);
		return repository.save(s);
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

		s.setId(studentModel.getId());
		s.setName(studentModel.getName());
		s.setPhone(studentModel.getPhone());
		s.setEmail(studentModel.getEmail());
		s.setUpdatedBy("John");
		s.setLastUpdatedBy(date);
		
		if (repository.existsById(s.getId())) {
			repository.save(s);
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
