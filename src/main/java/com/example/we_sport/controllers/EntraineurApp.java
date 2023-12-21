package com.example.we_sport.controllers;

import com.example.we_sport.DAO.EntraineurDAO;
import com.example.we_sport.Entity.Entraineur;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EntraineurApp extends Application {

    @FXML
    private TableView<Entraineur> tableView;
    @FXML
    private TextField searchField;

    private final EntraineurDAO entraineurDAO = new EntraineurDAO();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        initializeTableView();
        initializeSearchField();

        VBox vbox = new VBox(searchField, tableView);
        Scene scene = new Scene(vbox, 600, 400);

        primaryStage.setTitle("We Sport - Entraineurs");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadData();
    }

    private void initializeTableView() {
        TableColumn<Entraineur, Integer> idColumn = createTableColumn("ID", "entraineurID");
        TableColumn<Entraineur, Integer> nomColumn = createTableColumn("Nom", "nom");
        TableColumn<Entraineur, Integer> prenomColumn = createTableColumn("Prenom", "prenom");
        TableColumn<Entraineur, Integer> emailColumn = createTableColumn("Email", "email");
        TableColumn<Entraineur, Integer> telephoneColumn = createTableColumn("Telephone", "telephone");
        TableColumn<Entraineur, Integer> specialiteColumn = createTableColumn("Specialite", "specialite");

        TableColumn<Entraineur, Void> actionColumn = createActionColumn("Action");

        tableView.getColumns().addAll(idColumn, nomColumn, prenomColumn, emailColumn, telephoneColumn, specialiteColumn, actionColumn);
    }

    private TableColumn<Entraineur, Integer> createTableColumn(String text, String property) {
        TableColumn<Entraineur, Integer> column = new TableColumn<>(text);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

    private TableColumn<Entraineur, Void> createActionColumn(String text) {
        TableColumn<Entraineur, Void> actionColumn = new TableColumn<>(text);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = createDetailsButton();

            {
                btn.setOnAction(event -> showDetails(getTableView().getItems().get(getIndex())));
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });

        return actionColumn;
    }

    private Button createDetailsButton() {
        return new Button("Details");
    }

    private void initializeSearchField() {
        searchField = new TextField();
        searchField.setPromptText("Search by name");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> searchEntraineur(newValue));
    }

    private void showDetails(Entraineur entraineur) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/we_sport/EntraineurViews/EntraineurDetails.fxml"));
            Parent root = loader.load();

            EntraineurDetailsController detailsController = loader.getController();
            detailsController.loadEntraineurDetails(entraineur);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Entraineur Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        ObservableList<Entraineur> entraineurs = FXCollections.observableArrayList();
        entraineurs.addAll(entraineurDAO.getAllEntraineurs());
        tableView.setItems(entraineurs);
    }

    private void searchEntraineur(String name) {
        ObservableList<Entraineur> filteredEntraineurs = FXCollections.observableArrayList();
        filteredEntraineurs.addAll(entraineurDAO.getEntraineursByName(name));
        tableView.setItems(filteredEntraineurs);
    }
}
