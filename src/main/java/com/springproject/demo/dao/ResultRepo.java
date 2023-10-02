package com.springproject.demo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.Student;

public interface ResultRepo extends CrudRepository<Student, String>{
	List<Student> findByQuizcode(String qcode);
	Student findByQuizcodeAndName(String qcode,String name);
	List<Student> findByName(String name);
}
