package com.example.we_sport.Entity;

import java.util.Objects;

public class Adherent {
    private int adherentID;
    private String nom;
    private String prenom;
    private String email;

    public String getAdherentAdresse() {
        return Adresse;
    }

    public void setAdherenAdresse(String adresse) {
        Adresse = adresse;
    }

    private String motDePasse;
    private String telephone;
    private String Adresse;


    public Adherent() {
    }

    public Adherent(int adherentID, String nom, String prenom, String email, String motDePasse, String telephone) {
        this.adherentID = adherentID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
    }

    public int getAdherentID() {
        return adherentID;
    }

    public void setAdherentID(int adherentID) {
        this.adherentID = adherentID;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adherent adherent = (Adherent) o;
        return adherentID == adherent.adherentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(adherentID);
    }

    @Override
    public String toString() {
        return "Adherent{" +
                "adherentID=" + adherentID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

