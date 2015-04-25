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
<<<<<<< HEAD:app/src/main/java/com/example/wael/student/models/Course.java
<<<<<<< HEAD:app/src/main/java/com/example/wael/student/models/Course.java
    @SerializedName("date_depo_course")
    private String dateDepo;

    public Course(){}
    public Course(int idCourse, String name, String description, String url, String teacher, String dateDepo, String subject) {

        this.idCourse = idCourse;
=======
    private Date dateDepo;

    public Course(int id, String name, String description, String url, String subject, String teacher, Date dateDepo) {
        this.id = id;
>>>>>>> parent of 9566f76... implant git in android studio:app/src/main/java/com/example/wael/partie_etudiant/Models/Course.java
=======
    private String dateDepo;

    public Course(int id, String name, String description, String url, String teacher, String dateDepo, String subject) {
        this.id = id;
>>>>>>> parent of 38342ba... 24/04/2015:app/src/main/java/com/example/wael/partie_etudiant/Models/Course.java
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
<<<<<<< HEAD:app/src/main/java/com/example/wael/student/models/Course.java
<<<<<<< HEAD:app/src/main/java/com/example/wael/student/models/Course.java

=======
>>>>>>> parent of 38342ba... 24/04/2015:app/src/main/java/com/example/wael/partie_etudiant/Models/Course.java
    public String getDateDepo() {
=======
    public Date getDateDepo() {
>>>>>>> parent of 9566f76... implant git in android studio:app/src/main/java/com/example/wael/partie_etudiant/Models/Course.java
        return dateDepo;
    }

    public void setDateDepo(Date dateDepo) {
        this.dateDepo = dateDepo;
    }


}
