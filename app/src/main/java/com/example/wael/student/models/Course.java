package com.example.wael.student.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wael on 01/04/2015.
 */
public class Course {


    @SerializedName("id_course")
    private int idCourse;
    @SerializedName("nom_course")
    private String name;
    @SerializedName("description_course")
    private String description;
    @SerializedName("url_course")
    private String url;
    @SerializedName("id_subject_course")
    private String subject;
    @SerializedName("teacher_course")
    private String teacher;
    @SerializedName("date_depo_course")
    private String dateDepo;

    public Course(){}
    public Course(int idCourse, String name, String description, String url, String teacher, String dateDepo, String subject) {

        this.idCourse = idCourse;
        this.name = name;
        this.description = description;

        this.teacher = teacher;
        this.url = url;

        this.dateDepo=dateDepo;
        this.subject = subject;
    }


    public int getIdCourse() {
        return idCourse;
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
