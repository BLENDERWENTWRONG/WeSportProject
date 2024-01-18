package com.esprit.wesport.Entity;

public class Utilisateur {
    private int utilisateurId;
    private String email;
    private String mdp;

    private String role;

    public Utilisateur() {

    }
    public Utilisateur(int utilisateurIdid, String email, String mdp, String role) {
        this.utilisateurId = utilisateurId;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "utilisateur{" +
                "Id=" + utilisateurId +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

