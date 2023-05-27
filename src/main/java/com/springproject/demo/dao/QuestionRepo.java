package com.springproject.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.Question;

public interface QuestionRepo extends CrudRepository<Question, Integer>{

}
