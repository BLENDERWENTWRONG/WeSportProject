/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.wesport.Controllers;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.*;

import com.esprit.wesport.Entity.Activite;
import com.esprit.wesport.Entity.Adherent;
import com.esprit.wesport.Entity.CustomersData;
import com.esprit.wesport.Entity.Data;
import com.esprit.wesport.Utils.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;



public class mainFormController implements Initializable {
    
    @FXML
    private AnchorPane main_form;
    
    @FXML
    private Label username;
    
    @FXML
    private Button dashboard_btn;
    
    @FXML
    private Button inventory_btn;

    
    @FXML
    private AnchorPane inventory_form;
    
    @FXML
    private TableView<Activite> inventory_tableView;

    @FXML
    private TableColumn<Activite, String> inventory_col_activiteName;
    

    
    @FXML
    private ImageView inventory_imageView;
    

    @FXML
    private TextField inventory_activiteID;
    
    @FXML
    private TextField inventory_activiteName;
    

    
    @FXML
    private AnchorPane dashboard_form;
    

    
    @FXML
    private Label dashboard_NC;
    
    @FXML
    private Label dashboard_TI;
    
    @FXML
    private Label dashboard_TotalI;
    
    @FXML
    private Label dashboard_NSP;
    
    @FXML
    private AreaChart<String, Number> dashboard_incomeChart;

    @FXML
    private BarChart<String, Number> seancePerRegionAreaChart;


    
    private Alert alert;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    private int cID;
    private ObservableList<Activite> cardListData = FXCollections.observableArrayList();
    
    public void dashboardDisplayNC() {
        
        String sql = "SELECT COUNT(EntraineurID) FROM entraineur";
        connect = Database.connectDB();
        
        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                nc = result.getInt("COUNT(EntraineurID)");
            }
            dashboard_NC.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public void dashboardDisplayTI() {

        String sql = "SELECT COUNT(id) FROM activitedata";
        connect = Database.connectDB();

        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nc = result.getInt("COUNT(id)");
            }
            dashboard_TI.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTotalI() {

        String sql = "SELECT COUNT(certificationId) FROM certification";
        connect = Database.connectDB();

        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nc = result.getInt("COUNT(certificationId)");
            }
            dashboard_TotalI.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardNSP() {
        
        String sql = "SELECT COUNT(AdherentID) FROM adherent";
        
        connect = Database.connectDB();
        
        try {
            int q = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            if (result.next()) {
                q = result.getInt("COUNT(AdherentID)");
            }
            dashboard_NSP.setText(String.valueOf(q));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    


    public void inventoryAddBtn() {

        if (inventory_activiteName.getText().isEmpty()
                || Data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            // CHECK activite ID
//            String checkActiviteID = "SELECT activiteid FROM activitedata WHERE activiteid = '"
//                    + inventory_activiteID.getText() + "'";

            connect = Database.connectDB();




            try {
//
//                statement = connect.createStatement();
//                result = statement.executeQuery(checkActiviteID);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(inventory_activiteID.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO activitedata "
                            + "( activiteName, image) "
                            + "VALUES(?,?)";

                    prepare = connect.prepareStatement(insertData);
//                    prepare.setString(1, inventory_activiteID.getText());
                    prepare.setString(1, inventory_activiteName.getText());


                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(2, path);

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryUpdateBtn() {

        if (inventory_activiteName.getText().isEmpty()
                || Data.path == null || Data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = Data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE activitedata SET "
                    + "activiteName = '" + inventory_activiteName.getText()
                    + "', image = '" + path  + "' WHERE id = " + Data.id;

            connect = Database.connectDB();

            try {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE activite name: " + inventory_activiteName.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryDeleteBtn() {
        if (Data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE activite name: " + inventory_activiteName.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM activitedata WHERE id = " + Data.id;
                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    public void inventoryClearBtn() {

//        inventory_activiteID.setText("");
        inventory_activiteName.setText("");
        Data.path = "";
        Data.id = 0;
        inventory_imageView.setImage(null);


    }

    // LETS MAKE A BEHAVIOR FOR IMPORT BTN FIRST
    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }

    // MERGE ALL DATAS
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

    // TO SHOW DATA ON OUR TABLE
    private ObservableList<Activite> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = inventoryDataList();
        inventory_col_activiteName.setCellValueFactory(new PropertyValueFactory<>("activiteName"));

        inventory_tableView.setItems(inventoryListData);

    }

    public void inventorySelectData() {

        Activite prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        inventory_activiteName.setText(prodData.getActiviteName());
        Data.path = prodData.getImage();
        String path = "File:" + prodData.getImage();
        Data.id = prodData.getId();
        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }



    public void customerID() {

        String sql = "SELECT MAX(AdherentId) FROM adherent";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(AdherentId)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(AdherentId)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }

            Data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Adherent> customersDataList() {

        ObservableList<Adherent> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM adherent";
        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            Adherent cData;

            while (result.next()) {
                cData = new Adherent(result.getInt("AdherentId"),
                        result.getString("Nom"));

                listData.add(cData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<Adherent> customersListData;

//    public void customersShowData() {
//        customersListData = customersDataList();
//
//        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("adherentID"));
//        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("nom"));
//
//
//        customers_tableView.setItems(customersListData);
//    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);

            dashboardDisplayNC();
            dashboardDisplayTI();
            dashboardTotalI();

        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);

            inventoryShowData();
        }

    }

    public void displayUsername() {
        String user = Data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);

    }


    public void populateActivitiesPerMonthChart() {
        if (dashboard_incomeChart != null) {
            dashboard_incomeChart.getData().clear();

            String sql = "SELECT DATE_FORMAT(date_seance, '%Y-%m'), COUNT(*) FROM seance GROUP BY DATE_FORMAT(date_seance, '%Y-%m')";
            connect = Database.connectDB();
            XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    chartSeries.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
                }

                dashboard_incomeChart.getData().add(chartSeries);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void populateSeancePerRegionChart() {
        if (seancePerRegionAreaChart != null) {
            seancePerRegionAreaChart.getData().clear();

            String sql = "SELECT region, COUNT(*) FROM seance GROUP BY region";
            connect = Database.connectDB();
            XYChart.Series<String, Number> chartSeries = new XYChart.Series<>();

            try {
                prepare = connect.prepareStatement(sql);
                result = prepare.executeQuery();

                while (result.next()) {
                    chartSeries.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
                }

                seancePerRegionAreaChart.getData().add(chartSeries);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardTotalI();
        inventoryShowData();
        populateActivitiesPerMonthChart();
        populateSeancePerRegionChart();
    }
}

