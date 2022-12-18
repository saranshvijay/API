package com.fnp.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fnp.converter.StudentConverter;
import com.fnp.dto.Student;
import com.fnp.model.StudentModel;
import com.fnp.repository.StudentRepository;

@Repository
public class StudentService {

	@Autowired
	RedisTemplate<String, Student> redis;

	@Autowired
	StudentRepository repository;

	@Autowired
	StudentConverter converter;

	public Student saveStudent(StudentModel studentModel) {

		Student student = converter.saveConverter(studentModel);
		
		Student studentSaved = repository.save(student);
		redis.opsForHash().put("student", studentSaved.getId(), studentSaved);
		redis.expire("student",20, TimeUnit.SECONDS);

		return studentSaved;
	}

	public String deleteStudent(int id) {

		if (repository.existsById(id)) {
			repository.deleteById(id);
			redis.opsForHash().delete("student", id);
			return "deleted";
		} else {
			return "doesNotExist";
		}
	}

	public String updateStudent(StudentModel studentModel) {
		
		if (repository.existsById(studentModel.getId())) {
			Student student = converter.updateConverter(studentModel);
			repository.save(student);
			if(redis.hasKey("student")) { 
				if(redis.opsForHash().hasKey("student", student.getId())){
					redis.opsForHash().delete("student", student.getId());
					redis.opsForHash().put("student", student.getId(), student);
					redis.expire("student",20, TimeUnit.SECONDS);
				}
			}else {
				redis.opsForHash().put("student", student.getId(), student);
				redis.expire("student",20, TimeUnit.SECONDS);

			}
			return "Updated Successfully.";
		} else
			return "Record does not exist.";

	}

	public Student getStudent(int id) {
		
		if (redis.opsForHash().hasKey("student", id)) {
			Student student = (Student) redis.opsForHash().get("student", id);
			return student;
		} else if (repository.existsById(id)) {
			Optional<Student> obj = repository.findById(id);
			if (obj.isPresent()) {
				Student student = obj.get();
				redis.opsForHash().put("student", student.getId(), student);
				redis.expire("student",20, TimeUnit.SECONDS);
				return student;
			}
		}
		return null;
	}

	public List<Student> getAllStudents() {
		if (redis.hasKey("students")) {
			if (redis.opsForHash().hasKey("students", 5)) {
				return (List<Student>) redis.opsForHash().get("students", 5);
			}
		}
		List<Student> list = repository.findAll();
		redis.opsForHash().put("students", 5, list);
		redis.expire("students",20, TimeUnit.SECONDS);

		return list;
	}
}
