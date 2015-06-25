package rnu.iit.waelgroup.student.Models;

/**
 * Created by Wael on 30/03/2015.
 */
public class Test {

    private int id_test;
    private String subject;
    private String teacher;
    private String level;
    private int session;
    private String date;
    private String duration;
    private String courses;

    public Test(int id_test, String subject, String teacher, String level, int session, String date, String duration, String courses) {
        this.id_test = id_test;
        this.subject = subject;
        this.teacher = teacher;
        this.level = level;
        this.session = session;
        this.date = date;
        this.duration = duration;
        this.courses = courses;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSession() {
        return session;
    }

    public void setSession(int session) {
        this.session = session;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getId_test() {
        return id_test;
    }
}
