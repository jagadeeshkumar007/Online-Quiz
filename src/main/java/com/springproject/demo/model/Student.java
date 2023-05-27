package com.springproject.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="results")
public class Student {

	@Id
	@Column(length=20)
	private String rollno;
	@Column(name="result",nullable=true)
	private int result;
	@Column(name="name",nullable=true)
	private String name;
	@Column(name="email",nullable=true)
	private String email;
	@Column(name="branch",nullable=true)
	private String branch;

	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	
	
}
