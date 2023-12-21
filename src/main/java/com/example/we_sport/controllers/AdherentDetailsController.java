package com.example.we_sport.controllers;

import com.example.we_sport.Entity.Adherent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AdherentDetailsController {

    @FXML
    private Label idLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;


    @FXML
    public void initialize() {
        // Initialization code if needed
    }

    public void loadAdherentDetails(Adherent adherent) {
        idLabel.setText("ID: " + adherent.getAdherentID());
        nomLabel.setText("Nom: " + adherent.getNom());
        prenomLabel.setText("Prenom: " + adherent.getPrenom());
    }
}
