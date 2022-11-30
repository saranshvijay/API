package com.fnp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fnp.interceptor.StudentInterceptor;

@SpringBootApplication
@EnableJpaRepositories
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
		Date date = new Date();
		StudentInterceptor.dateStore = date;
	}
}
