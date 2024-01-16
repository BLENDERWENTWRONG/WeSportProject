package com.example.we_sport.controllers;

import com.example.we_sport.Entity.Adherent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class AdherentDetailsController {


    @FXML
    public Button confirm;
    @FXML

    public Button bloquerUser;
    @FXML

    public ImageView userImage;

    @FXML
    private TextField nomPrenom;
    @FXML
    private TextField email;
    @FXML
    private TextField  addresse;

    @FXML
    private TextField   numéro ;

    @FXML
    private TextField dateNaissance ;

    @FXML
    private LineChart<String, Number> userChart;

    public void initialize() {
      nomPrenom.setDisable(true);
      email.setDisable(true);
      addresse.setDisable(true);
      numéro.setDisable(true);
      dateNaissance.setDisable(true);
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Nombre de participation par séance");
        series1.getData().add(new XYChart.Data<>("Session 1", 10));
        series1.getData().add(new XYChart.Data<>("Session 2", 15));
        series1.getData().add(new XYChart.Data<>("Session 3", 8));

        userChart.getData().add(series1);
    }

    public void loadAdherentDetails(Adherent adherent) {
        nomPrenom.setText(adherent.getPrenom()+" "+adherent.getNom());
        email.setText(adherent.getEmail());
        addresse.setText(adherent.getAdherentAdresse());
        numéro.setText(adherent.getTelephone());
    }
}
