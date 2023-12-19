package com.example.we_sport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    private DatabaseConnector databaseConnector;

    public LoginController() {
        // Instantiate DatabaseConnector
        this.databaseConnector = new DatabaseConnector();
    }

    @FXML
    private void login(ActionEvent event) {
        try {
            validateFields();

            String email = emailField.getText();
            String password = passwordField.getText();

            // Replace this with your actual authentication logic using the database
            if (authenticateUser(email, password)) {
                // Pass the email to the dashboard
                ((wesport) wesport.getInstance()).loadDashboard(email);
            } else {
                showAlert("Login Failed", "Invalid email or password. Please try again.");
            }
        } catch (ValidationException | SQLException e) {
            showLoginErrorAlert("Error during login: " + e.getMessage());
        }
    }

    @FXML
    private void goToSignUp(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Signup.fxml");
    }

    private void validateFields() throws ValidationException {
        if (emailField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            throw new ValidationException("All fields are required. Please fill in all the fields.");
        }
    }

    // Replace this method with your actual authentication logic using the database
    private boolean authenticateUser(String email, String password) throws SQLException {
        try (Connection connection = databaseConnector.getConnection()) {
            String query = "SELECT * FROM utilisateur WHERE email = ? AND mot_de_passe = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If a row is found, authentication is successful
                }
            }
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showLoginErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private static class ValidationException extends Exception {
        public ValidationException(String message) {
            super(message);
        }
    }
}
