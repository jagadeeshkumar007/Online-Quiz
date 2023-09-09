package com.springproject.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.springproject.demo.model.Question;

import java.util.List;

public interface QuestionRepo extends CrudRepository<Question, Integer>{
    List<Question> findByQuizcode(String qcode);
}
