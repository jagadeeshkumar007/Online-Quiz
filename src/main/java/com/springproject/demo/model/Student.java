package com.springproject.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="results")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="result",nullable=true)
	private int result;
	@Column(name="name",nullable=true)
	private String name;
	@Column(name="quizcode",nullable=true)
	private String quizcode;
	private String qsno;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuizcode() {
		return quizcode;
	}
	public void setQuizcode(String quizcode) {
		this.quizcode = quizcode;
	}

	public String getQsno() {
		return qsno;
	}

	public void setQsno(String qsno) {
		this.qsno = qsno;
	}
}
