package com.example.we_sport.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SeanceData {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty date;
    private final SimpleStringProperty horaire;
    private final SimpleStringProperty description;
    private final SimpleStringProperty lieu;
    private final SimpleStringProperty region;




    public SeanceData(int id, String date, String horaire, String description, String lieu, String region) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.horaire = new SimpleStringProperty(horaire);
        this.description = new SimpleStringProperty(description);
        this.lieu = new SimpleStringProperty(lieu);
        this.region = new SimpleStringProperty(region);
    }


    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getHoraire() {
        return horaire.get();
    }

    public SimpleStringProperty horaireProperty() {
        return horaire;
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public String getLieu() {
        return lieu.get();
    }

    public SimpleStringProperty lieuProperty() {
        return lieu;
    }

    public String getRegion() {
        return region.get();
    }

    public SimpleStringProperty regionProperty() {
        return region;
    }
}
