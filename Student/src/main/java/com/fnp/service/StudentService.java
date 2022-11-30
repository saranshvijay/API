package com.fnp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fnp.dao.StudentRepository;
import com.fnp.dto.Student;

@Repository
public class StudentService {

	@Autowired
	StudentRepository repository;

	public Student saveStudent(Student student) {
		
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

	public String updateStudent(Student student) {

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
