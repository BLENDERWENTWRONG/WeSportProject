package com.example.we_sport.controllers;

import com.example.we_sport.DAO.EntraineurDAO;
import com.example.we_sport.Entity.Entraineur;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class EntraineurDetailsController {

    @FXML
    private Label idLabel;

    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label telephoneLabel;

    @FXML
    private Label specialiteLabel;
    @FXML
    private Label verificationStatusLabel;
    @FXML
    private Button verificationStatusButton;

    private Entraineur entraineur;


    private EntraineurDAO entraineurDAO = new
            EntraineurDAO();


    @FXML
    public void initialize() {
    }

    public void loadEntraineurDetails(Entraineur entraineur) {
        this.entraineur = entraineur;

        idLabel.setText("ID: " + entraineur.getEntraineurID());
        nomLabel.setText("Nom: " + entraineur.getNom());
        prenomLabel.setText("Prenom: " + entraineur.getPrenom());
        verificationStatusLabel.setText("Verification Status: " + (entraineur.getVerified() ? "Verified" : "Unverified"));
        verificationStatusButton.setText(entraineur.getVerified() ? "Deactivate Account" : "Activate Account");
    }


    public void activateAccount() {
        boolean newVerificationStatus = !entraineur.getVerified();
        entraineurDAO.updateVerificationStatus(entraineur.getEntraineurID(), newVerificationStatus);

        loadEntraineurDetails(entraineurDAO.getAllEntraineurs().get(entraineur.getEntraineurID()));
    }
}
