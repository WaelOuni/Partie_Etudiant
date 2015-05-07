package rnu.iit.waelgroup.student.Models;

/**
 * Created by Wael on 17/04/2015.
 */
public class Subject {

    private int id;
    private String subject_name;
    private String subject_teacher;


    public Subject(String subject_name ,String subject_teacher)
    {
        this.subject_name = subject_name;
        this.subject_teacher = subject_teacher;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_teacher() {
        return subject_teacher;
    }

    public void setSubject_teacher(String subject_teacher) {
        this.subject_teacher = subject_teacher;
    }

    public int getId() {
        return id;
    }
}
