package com.example.wael.student.models;

/**
 * Created by Wael on 17/04/2015.
 */
public class Subject {

    private int id;
    private String subject_name;

    public Subject(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }
}
