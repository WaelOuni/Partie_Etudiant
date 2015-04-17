package com.example.wael.partie_etudiant.Models;

/**
 * Created by Wael on 01/04/2015.
 */
public class Choice {
    private int id;
    private String value;

    public Choice(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
