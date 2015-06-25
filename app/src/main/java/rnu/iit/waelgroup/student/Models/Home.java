package rnu.iit.waelgroup.student.Models;

/**
 * Created by Wael on 08/05/2015.
 */
public class Home {
    private int idHome;
    private Course c;
    private Test t;
    public Home(int idHome,Course c ){
        this.idHome = idHome;
        this.c=c;
    }

    public Home(int idHome, Test t ) {
        this.idHome = idHome;
        this.t = t;
    }

    public int getIdHome() {
        return idHome;
    }

    public Course getC() {
        return c;
    }

    public void setC(Course c) {
        this.c = c;
    }

    public Test getT() {
        return t;
    }

    public void setT(Test t) {
        this.t = t;
    }
}
