package com.example.wael.student.models;

/**
 * Created by Wael on 30/03/2015.
 */
public class Etudiant {

    private  int cin;
    private String nom;
    private String prenom;
    private int inscription;
    private String genre;
    private String email;
    private String password;
    private String niveau;
    private String telephone;


    public Etudiant(int cin, String nom, String prenom, int inscription,
                    String genre, String email, String password, String niveau, String telephone){

        this.cin=cin;
        this.nom=nom;
        this.prenom=prenom;
        this.inscription=inscription;
        this.genre= genre;
        this.email=email;
        this.password=password;
        this.niveau=niveau;
        this.telephone=telephone;

    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
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

    public int getInscription() {
        return inscription;
    }

    public void setInscription(int inscription) {
        this.inscription = inscription;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
