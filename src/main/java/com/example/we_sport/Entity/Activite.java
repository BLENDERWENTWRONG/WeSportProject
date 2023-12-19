package com.example.we_sport.Entity;

import java.util.Objects;

public class Activite {
    private int activiteID;
    private String nomActivite;



    public Activite() {
    }

    public Activite(int activiteID, String nomActivite) {
        this.activiteID = activiteID;
        this.nomActivite = nomActivite;

    }

    public int getActiviteID() {
        return activiteID;
    }

    public void setActiviteID(int activiteID) {
        this.activiteID = activiteID;
    }

    public String getNomActivite() {
        return nomActivite;
    }

    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }


    @Override
    public String toString() {
        return "Activite{" +
                "activiteID=" + activiteID +
                ", nomActivite='" + nomActivite + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(activiteID);
    }

    // Méthode equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activite activite = (Activite) o;
        return activiteID == activite.activiteID;
    }
}

