/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cafe.Controllers;

import com.example.cafe.Entity.Activite;
import com.example.cafe.Entity.data;
import com.example.cafe.Utils.database;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

/**
 *
 * @author WINDOWS 10
 */
public class cardActiviteController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML




    private Label activite_name;

    @FXML
    private Label activite_price;

    @FXML
    private ImageView activite_imageView;

    @FXML
    private Spinner<Integer> activite_spinner;

    @FXML
    private Button activite_addBtn;

    private Activite activiteData;
    private Image image;

    private String activiteID;
    private String type;
    private String activite_date;
    private String activite_image;

    private SpinnerValueFactory<Integer> spin;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void setData(Activite activiteData) {
        this.activiteData = activiteData;

        activite_image = activiteData.getImage();
        activiteID = activiteData.getActiviteId();
        activite_name.setText(activiteData.getActiviteName());
        String path = "File:" + activiteData.getImage();
        image = new Image(path, 190, 94, false, true);
        activite_imageView.setImage(image);


    }
    private int qty;

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        activite_spinner.setValueFactory(spin);
    }

    private double totalP;
    private double pr;

    public void addBtn() {

        mainFormController mForm = new mainFormController();
        mForm.customerID();

        qty = activite_spinner.getValue();
        String check = "";


        connect = database.connectDB();

        try {
            int checkStck = 0;


            if(checkStck == 0){

                String updateStock = "UPDATE activitedata SET activite_name = '"
                            + activite_name.getText() + "', image = '"
                            + activite_image  + "' WHERE activite_id = '"
                            + activiteID + "'";
                prepare = connect.prepareStatement(updateStock);
                prepare.executeUpdate();

            }



            if (!check.equals("Available") || qty == 0) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Something Wrong :3");
                alert.showAndWait();
            } else {

                if (checkStck < qty) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid. This Activite is Out of stock");
                    alert.showAndWait();
                } else {
                    activite_image = activite_image.replace("\\", "\\\\");

                    String insertData = "INSERT INTO customer "
                            + "(customer_id, activite_id, activite_name, type, quantity, price, date, image, em_username) "
                            + "VALUES(?,?,?,?,?,?,?,?,?)";
                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, String.valueOf(data.cID));
                    prepare.setString(2, activiteID);
                    prepare.setString(3, activite_name.getText());
                    prepare.setString(4, type);
                    prepare.setString(5, String.valueOf(qty));

                    totalP = (qty * pr);
                    prepare.setString(6, String.valueOf(totalP));

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(7, String.valueOf(sqlDate));

                    prepare.setString(8, activite_image);
                    prepare.setString(9, data.username);

                    prepare.executeUpdate();

                    int upStock = checkStck - qty;



                    System.out.println("Date: " + activite_date);
                    System.out.println("Image: " + activite_image);

                    String updateStock = "UPDATE activitedata SET activite_name = '"
                            + activite_name.getText() + "', image = '"
                            + activite_image  + "' WHERE activite_id = '"
                            + activiteID + "'";

                    prepare = connect.prepareStatement(updateStock);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantity();
    }

}
