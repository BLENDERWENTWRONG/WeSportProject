package com.esprit.wesport.Entity;

import java.sql.Time;
import java.util.Date;
import java.util.Objects;

public class Seance {

    private int seanceID;
    private Date date_seance;
    private Time horaire_seance;
    private String description_seance;
    private String lieu;
    private int entraineurID;
    private int activiteID;

    public Seance() {
    }

    public Seance(int seanceID, Date date_seance, Time horaire_seance, String description_seance, String lieu, int entraineurID, int activiteID) {
        this.seanceID = seanceID;
        this.date_seance = date_seance;
        this.horaire_seance = horaire_seance;
        this.description_seance = description_seance;
        this.lieu = lieu;
        this.entraineurID = entraineurID;
        this.activiteID = activiteID;
    }

    public int getSeanceID() {
        return seanceID;
    }

    public void setSeanceID(int seanceID) {
        this.seanceID = seanceID;
    }

    public Date getDate_seance() {
        return date_seance;
    }

    public void setDate_seance(Date date_seance) {
        this.date_seance = date_seance;
    }

    public Time getHoraire_seance() {
        return horaire_seance;
    }

    public void setHoraire_seance(Time horaire_seance) {
        this.horaire_seance = horaire_seance;
    }

    public String getDescription_seance() {
        return description_seance;
    }

    public void setDescription_seance(String description_seance) {
        this.description_seance = description_seance;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getentraineurID() {
        return entraineurID;
    }

    public void setentraineurID(int entraineurID) {
        this.entraineurID = entraineurID;
    }

    public int getactiviteID() {
        return activiteID;
    }

    public void setactiviteID(int activiteID) {
        this.activiteID = activiteID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seance seance = (Seance) o;
        return seanceID == seance.seanceID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(seanceID);
    }

    @Override
    public String toString() {
        return "seance{" +
                "seanceID=" + seanceID +
                ", date_seance=" + date_seance +
                ", horaire_seance=" + horaire_seance +
                ", description_seance='" + description_seance + '\'' +
                ", lieu='" + lieu + '\'' +
                ", id_entraineur=" + entraineurID +
                ", id_activite=" + activiteID +
                '}';
    }
}
