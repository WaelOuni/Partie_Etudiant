package com.example.wael.student.models;

import java.util.Date;

/**
 * Created by Wael on 30/03/2015.
 */
public class Test {

    private int id_test;
    private String matiere;
    private String niveau;
    private int seance;
    private Date date;
    private String duree;
    private String coursprepare;
    private String numquestchoisis;

    public Test(int id_test, String matiere, String niveau, int seance, Date date, String duree, String coursprepare, String numquestchoisis) {
        this.id_test = id_test;
        this.matiere = matiere;
        this.niveau = niveau;
        this.seance = seance;
        this.date = date;
        this.duree = duree;
        this.coursprepare = coursprepare;
        this.numquestchoisis = numquestchoisis;
    }

    public int getId_test() {
        return id_test;
    }

    public void setId_test(int id_test) {
        this.id_test = id_test;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getSeance() {
        return seance;
    }

    public void setSeance(int seance) {
        this.seance = seance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getCoursprepare() {
        return coursprepare;
    }

    public void setCoursprepare(String coursprepare) {
        this.coursprepare = coursprepare;
    }

    public String getNumquestchoisis() {
        return numquestchoisis;
    }

    public void setNumquestchoisis(String numquestchoisis) {
        this.numquestchoisis = numquestchoisis;
    }
}
