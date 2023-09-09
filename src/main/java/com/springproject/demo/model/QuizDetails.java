package com.springproject.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="QuizDetails")
public class QuizDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String quizcode;
    private String noqs;
    private String Description;
    private String duration;
    //Start time
    private String stime;
    //End Time
    private String etime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizcode() {
        return quizcode;
    }

    public void setQuizcode(String quizcode) {
        this.quizcode = quizcode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getNoqs() {
        return noqs;
    }

    public void setNoqs(String noqs) {
        this.noqs = noqs;
    }
}
