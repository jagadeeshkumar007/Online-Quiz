package com.springproject.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stlogin")
public class StudentLogin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String sname;
	private String rollno;
	private String email;
	private String branchsec;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBranchsec() {
		return branchsec;
	}
	public void setBranchsec(String branchsec) {
		this.branchsec = branchsec;
	}
	
}
