package com.example.we_sport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class wesportController {

    @FXML
    private void goToSignUp(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Signup.fxml");
    }


    @FXML
    private void goToLogin(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Page d'accueil.fxml");
    }
}
