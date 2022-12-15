package com.fnp.service;

import java.util.List;
import java.util.Optional;

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
		System.out.println(studentModel);
		Student student = converter.saveConverter(studentModel);
		System.out.println(student);
		Student student1 = repository.save(student);
		redis.boundSetOps("setstudent").add(student1);
		redis.boundHashOps("hashstudent").put(student1.getId(), student1);

		return student1;
	}

	public String deleteStudent(int id) {
		System.out.println(id);
		if (redis.opsForHash().hasKey("studenthello", id)) {
			repository.deleteById(id);
			System.out.println(id + "jdlkdel");
			redis.opsForHash().delete("studenthello", id);
			return "deleted";
		} else if (repository.existsById(id)) {
			repository.deleteById(id);
			System.out.println(id + "jdlkdeldcd");
			redis.opsForHash().delete("studenthello", id);
			return "deleted";
		} else {
			return "doesNotExist";
		}
	}

	public String updateStudent(StudentModel studentModel) {

		if (redis.opsForHash().hasKey("studenthello", studentModel.getId())) {
			Student student = converter.updateConverter(studentModel);
			repository.save(student);
			redis.opsForHash().put("studenthello", studentModel.getId(), student);
			return "Updated Successfully.";
		} else if (repository.existsById(studentModel.getId())) {
			Student student = converter.updateConverter(studentModel);
			repository.save(student);
			redis.opsForHash().put("studenthello", studentModel.getId(), student);
			return "Updated Successfully.";
		} else
			return "Record does not exist.";

	}

	public Optional<Student> getStudent(int id) {

		if (redis.opsForHash().hasKey("studenthello", id)) {
			Student obj = (Student) redis.opsForHash().get("studenthello", id);
			return Optional.of(obj);
		} else if (repository.existsById(id)) {
			Optional<Student> obj = repository.findById(id);
			return obj;
		} else {
			return null;
		}
	}

	public List<Student> getAllStudents() {

		if (redis.hasKey("studentlist")) {
			if (redis.opsForHash().values("studentlist") != null) {
				System.out.println(redis.opsForHash().values("studentlist"));
				//System.out.println(redis.opsForHash().values("studentlist").stream().map(e -> (Student) e).collect(Collectors.toList()));
				return (List<Student> ) (Object) redis.opsForHash().values("studentlist");
			}
		}
		System.out.println(redis.boundHashOps("studentlist").values());
		List<Student> list = repository.findAll();
		System.out.println(redis.opsForHash().entries("studenthello"));
		redis.boundHashOps("studentlist").put("studentlist", list);
		
		return list;
	}
}
