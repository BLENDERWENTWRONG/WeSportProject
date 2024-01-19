package com.example.we_sport.Controllers;

import com.example.we_sport.DatabaseConnector;
import com.example.we_sport.wesport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {
    private String loggedInUserEmail;


    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void goToRegistration(ActionEvent event) {
        // Load the Registration page
        ((wesport) wesport.getInstance()).loadPage("Test.fxml");

    }
    @FXML
    private void goToSignUp(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Signup.fxml");
    }
    @FXML
    private void goToHome(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Home.fxml");
    }

    @FXML
    private void loginUser(ActionEvent event) {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showMissingFieldsAlert();
            return;
        }

        try (Connection connection = DatabaseConnector.getConnection()) {
            String loginQuery = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
            try (PreparedStatement loginStatement = connection.prepareStatement(loginQuery)) {
                loginStatement.setString(1, email);
                loginStatement.setString(2, password);

                ResultSet resultSet = loginStatement.executeQuery();

                if (resultSet.next()) {
                    // Login successful
                    // Redirect to the appropriate page based on the user's role (Adherent or Entraineur)
                    String role = resultSet.getString("Role");
                    ((wesport) wesport.getInstance()).loadPage(role.equals("Adherent") ? "Test.fxml" : "Test.fxml");
                } else {
                    showLoginErrorAlert("Invalid email or password.");
                }
            }
        } catch (SQLException e) {
            showLoginErrorAlert("Error during login.");
            e.printStackTrace();
        }



    }

    private void showMissingFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Both email and password are required. Please fill in both fields.");
        alert.showAndWait();
    }

    private void showLoginErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login Failed");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
