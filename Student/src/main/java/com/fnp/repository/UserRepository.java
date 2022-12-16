package com.fnp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fnp.dto.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
