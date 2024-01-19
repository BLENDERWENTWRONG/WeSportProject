package com.example.we_sport;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class wesport extends Application {

    private static wesport instance;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        instance = this;
        this.primaryStage = primaryStage;

        // Load the initial FXML file for the main scene
        loadPage("Home.fxml");
    }

    public void loadPage(String fxmlFileName) {
        try {
            // Load the FXML file for the specified page
            FXMLLoader fxmlLoader = new FXMLLoader(wesport.class.getResource(fxmlFileName));
            VBox root = fxmlLoader.load();

            Scene scene = new Scene(root, 1280, 620);


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

    private String loggedInUserEmail;

    public void setLoggedInUserEmail(String email) {
        this.loggedInUserEmail = email;
    }

    public String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }


}