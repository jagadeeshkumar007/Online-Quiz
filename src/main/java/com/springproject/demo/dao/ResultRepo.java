package com.springproject.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.Student;

public interface ResultRepo extends CrudRepository<Student, String>{


}
