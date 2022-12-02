package com.fnp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fnp.dto.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
