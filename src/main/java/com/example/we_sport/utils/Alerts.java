package com.example.we_sport.utils;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class Alerts {

    public static void alertUser(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("User Interaction");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void popUser(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("User Interaction");
        alert.setHeaderText("");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
