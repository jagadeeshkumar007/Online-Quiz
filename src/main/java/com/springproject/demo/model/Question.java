package com.springproject.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="question")
public class Question {
	@Id
	private Integer qno;
	@Column(name="qcontent",nullable = false)
	private String qcontent;
	@Column(name="op1",nullable = false)
	private String op1;
	@Column(name="op2",nullable = false)
	private String op2;
	@Column(name="op3",nullable = false)
	private String op3;
	@Column(name="op4",nullable = false)
	private String op4;
	@Column(name="ans",nullable = false)
	private int ans;
	public Integer getQno() {
		return qno;
	}
	public void setQno(Integer qno) {
		this.qno = qno;
	}
	public String getQcontent() {
		return qcontent;
	}
	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}
	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}
	public String getOp3() {
		return op3;
	}
	public void setOp3(String op3) {
		this.op3 = op3;
	}
	public String getOp4() {
		return op4;
	}
	public void setOp4(String op4) {
		this.op4 = op4;
	}
	public int getAns() {
		return ans;
	}
	public void setAns(int ans) {
		this.ans = ans;
	}
	
}
