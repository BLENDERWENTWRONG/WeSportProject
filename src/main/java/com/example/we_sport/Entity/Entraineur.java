package com.example.we_sport.Entity;

public class Entraineur {
    private int entraineurID;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String specialite;


    public Entraineur() {
    }

    public Entraineur(int entraineurID, String nom, String prenom, String email, String motDePasse, String telephone, String specialite) {
        this.entraineurID = entraineurID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.specialite = specialite;
    }

    public int getEntraineurID() {
        return entraineurID;
    }

    public void setEntraineurID(int entraineurID) {
        this.entraineurID = entraineurID;
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

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


    @Override
    public String toString() {
        return "Entraineur{" +
                "entraineurID=" + entraineurID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", telephone='" + telephone + '\'' +
                ", specialite='" + specialite + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entraineur that = (Entraineur) o;
        return entraineurID == that.entraineurID;
    }
}
