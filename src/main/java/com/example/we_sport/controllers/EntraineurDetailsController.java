package com.example.we_sport.controllers;

import com.example.we_sport.DAO.EntraineurDAO;
import com.example.we_sport.Entity.Entraineur;
import com.example.we_sport.Entity.certification;
import com.example.we_sport.utils.Alerts;
import com.example.we_sport.utils.CostumMapLayer;
import com.gluonhq.maps.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import com.dansoftware.pdfdisplayer.PDFDisplayer;


public class EntraineurDetailsController {


    @FXML
    public VBox entraineurBOX;

    @FXML
    public TextField dateNaissance;

    @FXML
    public TableView certificatTable;
    @FXML
    public Button confirm;
    @FXML
    public AnchorPane anchorPane;


    @FXML
    private TextField nomPrenom;

    @FXML
    private TableView<certification> tableView;



    @FXML
    private TextField email;

    @FXML
    private TextField numéro;

    @FXML
    private TextField spécialité;


    @FXML
    private TextField addresse;


    @FXML
    private TextField verificationStatusLabel;
    @FXML
    private Button verificationStatusButton;

    private Entraineur entraineur;

    private Blob certFile ;


    private final EntraineurDAO entraineurDAO = new
            EntraineurDAO();
    private  CertificationController certificationController ;

    private CostumMapLayer costumMapLayer;

    public void loadEntraineurDetails(Entraineur entraineur) {
        this.entraineur = entraineur;
        nomPrenom.setText(entraineur.getPrenom()+" "+entraineur.getNom().toUpperCase() );
        email.setText(entraineur.getEmail());
        numéro.setText(entraineur.getTelephone());
        addresse.setText(entraineur.getAddresse());
        verificationStatusLabel.setText("Verification Status: " + (entraineur.getVerified() ? "Verified" : "Unverified"));
        verificationStatusButton.setText(entraineur.getVerified() ? "Deactivate Account" : "Activate Account");
        verificationStatusButton.setStyle((!entraineur.getVerified() ? "-fx-background-color: red; " : "-fx-background-color: green; "));
    }


    public void activateAccount() {
        boolean newVerificationStatus = !entraineur.getVerified();
        entraineurDAO.updateVerificationStatus(entraineur.getEntraineurID(), newVerificationStatus);
       Alerts.alertUser("Le statut de vérification est en train d'être modifié! Prosuivre ?");
        loadEntraineurDetails(entraineurDAO.getAllEntraineurs().get(entraineur.getEntraineurID()));
    }

    @FXML
    public void initialize() {

        MapView mapView = creatMapView();
        anchorPane.getChildren().add(mapView);

        nomPrenom.setDisable(true);
        numéro.setDisable(true);
        email.setDisable(true);
        verificationStatusLabel.setDisable(true);
        spécialité.setDisable(true);

//        loadData();
//        TableColumn<certification, Integer> idColumn = new TableColumn<>("ID certification");
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("certificationID"));
//        idColumn.setResizable(true);
//        idColumn.setSortable(true);
//        tableView.getColumns().add(idColumn);
//
//        TableColumn<certification, Void> actionColumn = new TableColumn<>("Download");
//        idColumn.setResizable(true);
//        tableView.getColumns().add(actionColumn);
//        actionColumn.setCellFactory(param -> new TableCell<>() {
//            private final Button btn = new Button("Téléchager le fichier");
//            {
//                btn.setOnAction(event -> {
//                    certification certification = getTableView().getItems().get(getIndex());
//                    PDFDisplayer displayer = new PDFDisplayer();
//                    Stage stage = new Stage();
//                    stage.setMaximized(true);
//                    stage.setTitle("Certification");
//                    stage.setScene(new Scene(displayer.toNode()));
//                    stage.show();
//                    InputStream inputStream;
//                    try {
//                        inputStream = certification.getFichier().getBinaryStream();
//                    } catch (SQLException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    displayer.loadPDF(inputStream);
//
//                    try {
//                        inputStream.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                });
//            }
//
//
//
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(btn);
//                }
//            }
//        });

    }

    public void loadData() {
        ObservableList<certification> certifications = FXCollections.observableArrayList();
        certifications.addAll(certificationController.getCertByUserId(1));
        tableView.setItems(certifications);
    }
    public void confirmModification(){

        Entraineur newUserDetails = entraineurDAO.getEntraineur(this.entraineur.getEntraineurID());
      String  newEmail = email.getText();
      String  newAddr = addresse.getText();
      String  newTel = numéro.getText();
      String  newSpec = spécialité.getText();

        if (newTel.isEmpty()||newTel.isBlank()) {
            Alerts.alertUser("Vérifier numéro");
        } else {
            newUserDetails.setTelephone(newTel);
        }
        if (newEmail.isEmpty()||newEmail.isBlank()) {
            Alerts.alertUser("Vérifier Email");
        } else {
            newUserDetails.setEmail(newTel);
        }
        if (newAddr.isEmpty()||newAddr.isBlank()) {
            Alerts.alertUser("Vérifier addresse");
        } else {
            newUserDetails.setAddresse(newTel);
        }
        if (newSpec.isEmpty()||newSpec.isBlank()) {
            Alerts.alertUser("Vérifier spécialité");
        } else {
            newUserDetails.setSpecialite(newTel);
        }

        if (entraineurDAO.updateEntraineur(newUserDetails)){
            Alerts.popUser("Opération réussi");
        }else {
            Alerts.alertUser("ERROR");
        };
    }

    private MapView creatMapView()
    {
        MapPoint tunis = new MapPoint(33.8869, 9.5375);
        MapView mapView = new MapView();
        CostumMapLayer costumMapLayer1 = new CostumMapLayer(tunis);
        mapView.addLayer(costumMapLayer1);
        mapView.setPrefSize(332,160);
        mapView.flyTo(0,tunis,1);
        mapView.setZoom(5);
        return mapView;
    }


}
