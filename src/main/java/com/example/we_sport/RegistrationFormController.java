package com.example.we_sport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

public class RegistrationFormController {
    @FXML
    private void goToLogin(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Page d'accueil.fxml");
    }

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField motDePasseField;

    @FXML
    private TextField telephoneField;

    @FXML
    private TextField adresseField;

    @FXML
    private CheckBox adherentCheckBox;

    @FXML
    private CheckBox entraineurCheckBox;



    @FXML
    private void registerUser(ActionEvent event) {
        if(areFieldsEmpty() && !adherentCheckBox.isSelected() && !entraineurCheckBox.isSelected()){
             empty();
        }
        else if(areFieldsValid() && areFieldsFilled()) {
            if (!adherentCheckBox.isSelected() && !entraineurCheckBox.isSelected()) {
                showRegistrationErrorAlert1("Please select at least one role (Adherent or Entraineur).");
                return;  // Stop registration if no role is selected
            }
            try (Connection connection = DatabaseConnector.getConnection()) {
                String utilisateurInsertSql = "INSERT INTO utilisateur (email, mot_de_passe, Role) VALUES (?, ?, ?)";
                try (PreparedStatement utilisateurStatement = connection.prepareStatement(utilisateurInsertSql, Statement.RETURN_GENERATED_KEYS)) {
                    utilisateurStatement.setString(1, emailField.getText());
                    utilisateurStatement.setString(2, motDePasseField.getText());
                    String role = "";

                    if (adherentCheckBox.isSelected()) {
                        role = "Adherent";
                    } else if (entraineurCheckBox.isSelected()) {
                        role = "Entraineur";
                    }
                    utilisateurStatement.setString(3, role);

                    int utilisateurAffectedRows = utilisateurStatement.executeUpdate();
                    if (utilisateurAffectedRows > 0) {
                        ResultSet utilisateurKeys = utilisateurStatement.getGeneratedKeys();
                        if (utilisateurKeys.next()) {
                            int utilisateurID = utilisateurKeys.getInt(1);

                            String adherentInsertSql = "INSERT INTO adherent (AdherentID, Nom, Prenom, Email, MotDePasse, Telephone, Adresse) VALUES (?, ?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement adherentStatement = connection.prepareStatement(adherentInsertSql)) {
                                adherentStatement.setInt(1, utilisateurID);
                                adherentStatement.setString(2, nomField.getText());
                                adherentStatement.setString(3, prenomField.getText());
                                adherentStatement.setString(4, emailField.getText());
                                adherentStatement.setString(5, motDePasseField.getText());
                                adherentStatement.setString(6, telephoneField.getText());
                                adherentStatement.setString(7, adresseField.getText());

                                int adherentAffectedRows = adherentStatement.executeUpdate();
                                if (adherentAffectedRows > 0) {
                                    showRegistrationSuccessAlert();
                                    clearFields();
                                } else {
                                    showRegistrationErrorAlert1("Adherent registration failed.");
                                }
                            }
                        } else {
                            showRegistrationErrorAlert1("Couldn't retrieve utilisateurID.");
                        }
                    } else {
                        showRegistrationErrorAlert1("Utilisateur registration failed.");
                    }
                } catch (SQLException e) {
                    showRegistrationErrorAlert1("Error during registration.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                showRegistrationErrorAlert1("Error during database connection.");
                e.printStackTrace();
            }
        }
    }

    private boolean areFieldsValid() {
        return isFieldValid(isNameValid(), "Invalid Name") &&
                isFieldValid(isPrenomValid(), "Invalid Prenom") &&
                isFieldValid(isEmailValid(), "Invalid Email missing @") &&
                isFieldValid(isMotDePasseValid(), "Invalid Mot de Passe minimum 6 characters")&&
                isFieldValid(isTelephoneValid(), "Invalid Telephone- must be 8 numbers") &&
                isFieldValid(isAdresseValid(), "Invalid Adresse minimum 10 characters") ;

    }

    private boolean isFieldValid(ValidationResult validationResult, String errorMessage) {
        if (!validationResult.isValid()) {
            showRegistrationErrorAlert1(errorMessage);
            return false;
        }
        return true;
    }

    private ValidationResult isNameValid() {
        String name = nomField.getText().trim();
        return new ValidationResult(!name.isEmpty() && name.matches("[a-zA-Z]+"), "Invalid Name");
    }

    private ValidationResult isPrenomValid() {
        String prenom = prenomField.getText().trim();
        return new ValidationResult(!prenom.isEmpty() && prenom.matches("[a-zA-Z]+"), "Invalid Prenom");
    }

    private ValidationResult isEmailValid() {
        String email = emailField.getText().trim();
        return new ValidationResult(!email.isEmpty() && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"), "Invalid Email missing @");
    }

    private ValidationResult isTelephoneValid() {
        String telephone = telephoneField.getText().trim();
        return new ValidationResult(!telephone.isEmpty() && telephone.matches("\\d{8}"), "Invalid Telephone- must be 8 numbers");
    }

    private ValidationResult isAdresseValid() {
        String adresse = adresseField.getText().trim();
        return new ValidationResult(!adresse.isEmpty() && adresse.length() >= 10, "Invalid Adresse minimum 10 characters");
    }

    private ValidationResult isMotDePasseValid() {
        String motDePasse = motDePasseField.getText().trim();
        return new ValidationResult(!motDePasse.isEmpty() && motDePasse.length() >= 6, "Invalid Mot de Passe minimum 6 characters");
    }

    private boolean areFieldsFilled() {
        return !nomField.getText().isEmpty() &&
                !prenomField.getText().isEmpty() &&
                !emailField.getText().isEmpty() &&
                !motDePasseField.getText().isEmpty() &&
                !telephoneField.getText().isEmpty() &&
                !adresseField.getText().isEmpty();

    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        emailField.clear();
        motDePasseField.clear();
        telephoneField.clear();
        adresseField.clear();
        adherentCheckBox.setSelected(false);
        entraineurCheckBox.setSelected(false);
    }

    private void showMissingFieldsAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All fields are required. Please fill in all the fields.");
        alert.showAndWait();
    }

    private void showRegistrationSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText(null);
        alert.setContentText("User registered successfully!");
        alert.showAndWait();
    }

    private void showRegistrationErrorAlert1(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);

        String detailedMessage = "Registration failed. Please check the following:\n" + message +
                "\nPlease correct the highlighted fields.";

        alert.setContentText(detailedMessage);
        alert.showAndWait();
    }

    private static class ValidationResult {
        private final boolean isValid;
        private final String errorMessage;

        public ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
    private boolean areFieldsEmpty() {
        return nomField.getText().isEmpty() &&
                prenomField.getText().isEmpty() &&
                emailField.getText().isEmpty() &&
                motDePasseField.getText().isEmpty() &&
                telephoneField.getText().isEmpty() &&
                adresseField.getText().isEmpty();
    }
    private void empty() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("All fields are empty");
        alert.showAndWait();
    }

}
