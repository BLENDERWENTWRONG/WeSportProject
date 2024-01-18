
package com.esprit.wesport.Controllers;

import com.esprit.wesport.Entity.Data;
import com.esprit.wesport.Entity.Activite;
import com.esprit.wesport.Utils.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class cardActiviteController implements Initializable {

    @FXML
    private VBox mainVBox;
    private Connection connect;

    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    public ObservableList<Activite> inventoryDataList() {

        ObservableList<Activite> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM activitedata";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Activite prodData;

            while (result.next()) {

                prodData = new Activite(result.getInt("id"),
                        result.getString("activiteName"),
                        result.getString("image") );
                listData.add(prodData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Activite> activities = inventoryDataList();

        for (Activite activity : activities) {
            VBox activityCard = createActivityCard(activity);
            mainVBox.getChildren().add(activityCard);
        }
    }
    private VBox createActivityCard(Activite activity) {
        VBox card = new VBox();
        card.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px; -fx-padding: 10px; -fx-background-radius: 10px;");

        ImageView imageView = new ImageView();
        Image image = new Image(activity.getImage());
        imageView.setImage(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);

        Label nameLabel = new Label(activity.getActiviteName());
        nameLabel.setStyle("-fx-text-fill: #333; -fx-font-size: 16px;");

        card.getChildren().addAll(imageView, nameLabel);

        return card;
    }
}
