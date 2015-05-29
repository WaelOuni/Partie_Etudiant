package rnu.iit.waelgroup.student.Models;

/**
 * Created by Wael on 30/03/2015.
 */
public class Resultat {

    private String nom;
    private String prenom;
    private int numrepjust;
    private int numrepfalse;
    private String mention;
    private int rapidite;
    private String subject_test;


    public Resultat(String nom, String prenom, int numrepjust, int numrepfalse, String mention, int rapidite, String subject_test) {
        this.nom = nom;
        this.prenom = prenom;
        this.numrepjust = numrepjust;
        this.numrepfalse = numrepfalse;
        this.mention = mention;
        this.rapidite = rapidite;
        this.subject_test = subject_test;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getSubject_test() {
        return subject_test;
    }

    public void setSubject_test(String subject_test) {
        this.subject_test = subject_test;
    }

    public int getNumrepjust() {
        return numrepjust;
    }

    public void setNumrepjust(int numrepjust) {
        this.numrepjust = numrepjust;
    }

    public int getNumrepfalse() {
        return numrepfalse;
    }

    public void setNumrepfalse(int numrepfalse) {
        this.numrepfalse = numrepfalse;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public int getRapidite() {
        return rapidite;
    }

    public void setRapidite(int rapidite) {
        this.rapidite = rapidite;
    }
}
