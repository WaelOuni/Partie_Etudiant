package com.example.wael.partie_etudiant.Models;

import java.util.Date;

/**
 * Created by Wael on 01/04/2015.
 */
public class Course {
    private int id;
    private String name;
    private String description;
    private String url;
    private String subject;
    private String teacher;
    private Date dateDepo;

    public Course(int id, String name, String description, String url, String subject, String teacher, Date dateDepo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject = subject;
        this.teacher = teacher;
        this.url = url;
        this.dateDepo=dateDepo;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubject(String url) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTeacher() {
        return teacher;
    }
    public Date getDateDepo() {
        return dateDepo;
    }

    public void setDateDepo(Date dateDepo) {
        this.dateDepo = dateDepo;
    }


}
