package com.example.we_sport.Controllers;

import com.example.we_sport.wesport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class HomeController {
    @FXML
    private Button facebookButton;

    @FXML
    private Button twitterButton;

    @FXML
    private void initialize() {
        // Set event handlers for the buttons
        facebookButton.setOnAction(this::openFacebookLink);
        twitterButton.setOnAction(this::openTwitterLink);
    }
@FXML
    private void openFacebookLink(ActionEvent event) {
        openLink("https://www.facebook.com/yourfacebookpage");
    }
@FXML
    private void openTwitterLink(ActionEvent event) {
        openLink("https://twitter.com/yourtwitterhandle");
    }

    private void openLink(String url) {
        // Open the link in the default web browser
        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception e) {
            showLinkErrorAlert();
            e.printStackTrace();
        }
    }

    private void showLinkErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Unable to open the link. Please try again later.");
        alert.showAndWait();
    }




    @FXML
    private Button homeButton;
    @FXML
    private void goToSignUp(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Signup.fxml");
    }


    @FXML
    private void goToLogin(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("LogIn.fxml");
    }

    @FXML
    private void goToHome(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Home.fxml");
    }

}
