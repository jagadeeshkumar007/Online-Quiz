package com.springproject.demo.dao;

import com.springproject.demo.model.QuizDetails;
import com.springproject.demo.model.StudentLogin;
import org.springframework.data.repository.CrudRepository;
import java.util.*;

public interface QuizDetailsRepo extends CrudRepository<QuizDetails, Integer> {
    List<QuizDetails> findByQuizcode(String quizcode);
}
