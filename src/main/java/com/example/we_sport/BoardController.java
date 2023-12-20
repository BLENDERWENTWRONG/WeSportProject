package com.example.we_sport;

import com.example.we_sport.Entity.seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

public class BoardController  {

    @FXML
    private TableView<seance> tableView;
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


}
