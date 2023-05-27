package com.springproject.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.StudentLogin;

public interface StudentloginRepo extends CrudRepository<StudentLogin, Integer> {

}
