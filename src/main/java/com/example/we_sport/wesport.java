package com.example.we_sport;

import com.example.we_sport.utils.GeoApi;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class wesport extends Application {
    private static wesport instance;
    private Stage primaryStage;
    private GeoApi geoApi;
    @Override
    public void start(Stage primaryStage) throws IOException {
        instance = this;
        this.primaryStage = primaryStage;
        GeoApi.getInfosFromCity();
//        loadPage("AdherentViews/AdherentDetails.fxml");
        loadPage("EntraineurViews/EntraineurDetails.fxml");
    }

    public void loadPage(String fxmlFileName) {
        try {
            // Load the FXML file for the specified page
            FXMLLoader fxmlLoader = new FXMLLoader(wesport.class.getResource(fxmlFileName));
            VBox root = fxmlLoader.load();

            // Create the scene
            Scene scene = new Scene(root);

            // Get the screen width


            // Set the scene width to the screen width
            scene.getWidth();

            // Set the stage title and scene
            primaryStage.setTitle("We Sport");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static wesport getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch();
    }
}
