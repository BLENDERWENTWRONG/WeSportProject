package com.example.we_sport;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BoardController {

    @FXML
    private Label userEmailLabel;

    // Méthode pour initialiser l'adresse e-mail après la connexion
    public void setUserEmail(String userEmail) {
        userEmailLabel.setText("Welcome " + userEmail);
    }
    @FXML
    private void handleDeconnexion() {
        // Implement your logout logic here
        // For example, return to the login page
        wesport.getInstance().loadPage("page d'accueil.fxml");
    }

    // Vous pouvez ajouter d'autres méthodes et de la logique ici

}
