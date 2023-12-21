package com.example.we_sport.controllers;

import com.example.we_sport.DAO.AdherentDAO;
import com.example.we_sport.DatabaseConnector;
import com.example.we_sport.Entity.Adherent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdherentApp {

    @FXML
    private TableView<Adherent> tableView;
    @FXML
    private TextField searchField;

    private AdherentDAO adherentDAO = new AdherentDAO();

    public void initialize() {
        TableColumn<Adherent, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("adherentID"));

        TableColumn<Adherent, String> nomColumn = new TableColumn<>("Nom");
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        TableColumn<Adherent, String> prenomColumn = new TableColumn<>("Prenom");
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        TableColumn<Adherent, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Adherent, String> telephoneColumn = new TableColumn<>("Telephone");
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        TableColumn<Adherent, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Details");

            {
                btn.setOnAction(event -> {
                    Adherent adherent = getTableView().getItems().get(getIndex());
                    showDetails(adherent);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        tableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, emailColumn, telephoneColumn, actionColumn);

        loadData();
    }

    private void showDetails(Adherent adherent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/we_sport/AdherentViews/AdherentDetails.fxml"));
            Parent root = loader.load();

            AdherentDetailsController detailsController = loader.getController();
            detailsController.loadAdherentDetails(adherent);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Adherent Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadData() {
        ObservableList<Adherent> adherents = FXCollections.observableArrayList();

        adherents.addAll(adherentDAO.getAllAdherents());

        tableView.setItems(adherents);
    }


    @FXML
    private void searchAdherent() {
        String name = searchField.getText();
        ObservableList<Adherent> filteredAdherents = FXCollections.observableArrayList();

        for (Adherent adherent : tableView.getItems()) {
            if (adherent.getNom().toLowerCase().contains(name.toLowerCase())) {
                filteredAdherents.add(adherent);
            }
        }

        tableView.setItems(filteredAdherents);
    }
}
