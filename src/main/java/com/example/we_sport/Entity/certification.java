package com.example.we_sport.Entity;


import java.sql.Blob;
import java.util.Date;
import java.util.Objects;

public class certification {
    private int certificationID;
    private int entraineurID;
    private Blob fichier ;
    private Date date_insertion;

    public certification() {
    }

    public certification(int certificationID, int entraineurID, Blob fichier, Date date_insertion) {
        this.certificationID = certificationID;
        this.entraineurID = entraineurID;
        this.fichier = fichier;
        this.date_insertion = date_insertion;
    }

    public int getCertificationID() {
        return certificationID;
    }

    public void setCertificationID(int certificationID) {
        this.certificationID = certificationID;
    }

    public int getEntraineurID() {
        return entraineurID;
    }

    public void setEntraineurID(int entraineurID) {
        this.entraineurID = entraineurID;
    }

    public Blob getFichier() {
        return fichier;
    }

    public void setFichier(Blob fichier) {
        this.fichier = fichier;
    }

    public Date getDate_insertion() {
        return date_insertion;
    }

    public void setDate_insertion(Date date_insertion) {
        this.date_insertion = date_insertion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        certification that = (certification) o;
        return certificationID == that.certificationID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(certificationID);
    }

    @Override
    public String toString() {
        return "certification{" +
                "certificationID=" + certificationID +
                ", entraineurID=" + entraineurID +
                ", fichier=" + fichier +
                ", date_insertion=" + date_insertion +
                '}';
    }
}
