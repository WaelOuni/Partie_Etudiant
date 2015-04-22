package com.example.wael.partie_etudiant.Models;

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
    private String dateDepo;

    public Course(int id, String name, String description, String url, String teacher, String dateDepo, String subject) {
        this.id = id;
        this.name = name;
        this.description = description;

        this.teacher = teacher;
        this.url = url;
        this.dateDepo=dateDepo;
        this.subject = subject;
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
    public String getDateDepo() {
        return dateDepo;
    }

    public void setDateDepo(String dateDepo) {
        this.dateDepo = dateDepo;
    }


}
