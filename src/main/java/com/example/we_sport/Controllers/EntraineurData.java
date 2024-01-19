package com.example.we_sport.Controllers;


public class EntraineurData {
    private final String nom;
    private final String prenom;

    public EntraineurData(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
