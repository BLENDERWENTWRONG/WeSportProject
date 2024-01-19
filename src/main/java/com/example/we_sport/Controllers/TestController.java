package com.example.we_sport.Controllers;

import com.example.we_sport.wesport;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import org.controlsfx.control.Rating;

import com.example.we_sport.DatabaseConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestController {
    private static final int ADHERENT_ID_CONST = 18;
    @FXML
    private TableView<SeanceData> seanceTableView;

    @FXML
    private TableColumn<SeanceData, Integer> idColumn;

    @FXML
    private TableColumn<SeanceData, String> dateColumn;

    @FXML
    private TableColumn<SeanceData, String> horaireColumn;

    @FXML
    private TableColumn<SeanceData, String> descriptionColumn;

    @FXML
    private TableColumn<SeanceData, String> lieuColumn;

    @FXML
    private TableColumn<SeanceData, String> regionColumn;

    private ObservableList<SeanceData> seanceDataList = FXCollections.observableArrayList();



    @FXML
    private void initialize() {
        // Configurez les colonnes
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        horaireColumn.setCellValueFactory(cellData -> cellData.getValue().horaireProperty());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
        lieuColumn.setCellValueFactory(cellData -> cellData.getValue().lieuProperty());
        regionColumn.setCellValueFactory(cellData -> cellData.getValue().regionProperty());

        // Remplissez le tableau avec les données depuis la base de données
        loadDataFromDatabase();
        addButton2();
    }
    public void logout(ActionEvent event) {
        // Handle logout logic here
        ((wesport) wesport.getInstance()).loadPage("Home.fxml");
    }



    private void addButton2() {
        TableColumn<SeanceData, Void> actionColumn = new TableColumn<>("Avis");

        Callback<TableColumn<SeanceData, Void>, TableCell<SeanceData, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<SeanceData, Void> call(final TableColumn<SeanceData, Void> param) {
                return new TableCell<>() {
                    private final Button btn1 = new Button("Cliquez ici pour donner votre avis");

                    {
                        btn1.setOnAction(e -> {
                            SeanceData data = getTableView().getItems().get(getIndex());
                            showSeanceDetails(data);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn1);
                        }
                    }
                };
            }
        };

        actionColumn.setCellFactory(cellFactory);
        seanceTableView.getColumns().add(actionColumn);
    }



    private void showSeanceDetails(SeanceData seanceData) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Détails de la séance");
        dialog.initStyle(StageStyle.UTILITY);

        Label label = new Label("Détails de la séance (ID: " + seanceData.getId() + ")");
        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setText(
                "Date: " + seanceData.getDate() + "\n" +
                        "Horaire: " + seanceData.getHoraire() + "\n" +
                        "Description: " + seanceData.getDescription() + "\n" +
                        "Lieu: " + seanceData.getLieu() + "\n" +
                        "Region: " + seanceData.getRegion()
        );

        Rating starRating = new Rating(); // Ajout du composant Rating

        Label avisLabel = new Label("Votre avis:");
        TextField avisTextField = new TextField();

        Button soumettreAvisButton = new Button("Soumettre Avis");
        Button btn = soumettreAvisButton; // Declare btn here

        soumettreAvisButton.setOnAction(e -> {
            String avisCommentaire = avisTextField.getText();
            boolean success = enregistrerAvis(seanceData, ADHERENT_ID_CONST, avisCommentaire); // Save the avis directly
            showAvisAddedDialog(success);

            // Mettez à jour le texte du bouton après avoir donné l'avis avec succès
            if (success) {
                btn.setText("Voir ou modifier avis");
                btn.setText("Voir ou modifier avis");
                dialog.getDialogPane().getChildren().removeAll(avisLabel, avisTextField, soumettreAvisButton);
            }
        });

        VBox content = new VBox(10, label, textArea, starRating, avisLabel, avisTextField, soumettreAvisButton);
        dialog.getDialogPane().setContent(content);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.showAndWait();
    }


    @FXML
    private void goToHome(ActionEvent event) {
        // Load the SignUp page
        ((wesport) wesport.getInstance()).loadPage("Home.fxml");
    }
    private void showAvisAddedDialog(boolean success) {
        Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setTitle(success ? "Avis ajouté" : "Erreur");
        alert.setHeaderText(null);
        alert.setContentText(success ? "Avis ajouté avec succès." : "Erreur lors de l'ajout de l'avis.");
        alert.showAndWait();
    }


    private void loadDataFromDatabase() {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT * FROM seance";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    SeanceData seance = new SeanceData(
                            resultSet.getInt("ID_seance"),
                            resultSet.getString("date_seance"),
                            resultSet.getString("horaire_seance"),
                            resultSet.getString("description_seance"),
                            resultSet.getString("lieu"),
                            resultSet.getString("region")
                    );
                    seanceDataList.add(seance);
                }

                seanceTableView.setItems(seanceDataList);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean enregistrerAvis(SeanceData seanceData, int adherent, String avisCommentaire) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO avis (adherentId, seanceId, commentaire) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ADHERENT_ID_CONST);
                preparedStatement.setInt(2, seanceData.getId());
                preparedStatement.setString(3, avisCommentaire);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Si au moins une ligne a été affectée, l'ajout est réussi
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Gérer l'exception selon vos besoins
        }
    }

}
