package rnu.iit.waelgroup.student.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Wael on 30/03/2015.
 */
public class Resultat {

    @SerializedName("cin_etudiant")
    private int cin;
    private int numrepjust;
    private int numrepfalse;
    private String mention;
    private int rapidite;
    private int idtest;

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getIdtest() {
        return idtest;
    }

    public void setIdtest(int idtest) {
        this.idtest = idtest;
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

    public Resultat(int cin, int idtest, int numrepjust, int numrepfalse, String mention, int rapidite) {
        this.cin = cin;
        this.idtest = idtest;
        this.numrepjust = numrepjust;
        this.numrepfalse = numrepfalse;
        this.mention = mention;
        this.rapidite = rapidite;

    }
}
