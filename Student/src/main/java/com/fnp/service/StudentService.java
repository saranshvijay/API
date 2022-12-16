package com.fnp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fnp.converter.StudentConverter;
import com.fnp.dto.Student;
import com.fnp.model.StudentModel;
import com.fnp.repository.StudentRepository;

@Repository
public class StudentService {

	@Autowired
	StudentRepository repository;

	@Autowired
	StudentConverter converter;

	public Student saveStudent(StudentModel studentModel) {

		Student student = converter.saveConverter(studentModel);

		Student student1 = repository.save(student);
		return student1;
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

		if (repository.existsById(studentModel.getId())) {
			Student student = converter.updateConverter(studentModel);
			repository.save(student);

			return "Updated Successfully.";
		} else
			return "Record does not exist.";

	}

	public Optional<Student> getStudent(int id) {

		if (repository.existsById(id)) {
			Optional<Student> obj = repository.findById(id);
			return obj;
		} else {
			return null;
		}
	}

	public List<Student> getAllStudents() {

		List<Student> list = repository.findAll();

		return list;
	}
}
