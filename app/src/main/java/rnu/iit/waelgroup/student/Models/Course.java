package rnu.iit.waelgroup.student.Models;

/**
 * Created by Wael on 01/04/2015.
 */
public class Course {
    private int id;
    private String name;
    private String description;
    private String url;
    private String teacher;
    private String dateDepo;
    private String subject;

    public Course(int idCourse, String name, String description, String url, String teacher, String dateDepo, String subject) {


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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDateDepo() {
        return dateDepo;
    }

    public void setDateDepo(String dateDepo) {
        this.dateDepo = dateDepo;
    }
}
