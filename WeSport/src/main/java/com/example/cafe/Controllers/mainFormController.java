/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cafe.Controllers;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.example.cafe.Entity.Activite;
import com.example.cafe.Entity.Adherent;
import com.example.cafe.Entity.customersData;
import com.example.cafe.Entity.data;
import com.example.cafe.Utils.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;


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
    private Button menu_btn;
    
    @FXML
    private Button customers_btn;
    
    @FXML
    private Button logout_btn;
    
    @FXML
    private AnchorPane inventory_form;
    
    @FXML
    private TableView<Activite> inventory_tableView;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_activiteID;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_activiteName;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_type;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_stock;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_price;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_status;
    
    @FXML
    private TableColumn<Activite, String> inventory_col_date;
    
    @FXML
    private ImageView inventory_imageView;
    
    @FXML
    private Button inventory_importBtn;
    
    @FXML
    private Button inventory_addBtn;
    
    @FXML
    private Button inventory_updateBtn;
    
    @FXML
    private Button inventory_clearBtn;
    
    @FXML
    private Button inventory_deleteBtn;
    
    @FXML
    private TextField inventory_activiteID;
    
    @FXML
    private TextField inventory_activiteName;
    
    @FXML
    private TextField inventory_stock;
    
    @FXML
    private TextField inventory_price;
    
    @FXML
    private ComboBox<?> inventory_status;
    
    @FXML
    private ComboBox<?> inventory_type;
    
    @FXML
    private AnchorPane menu_form;
    
    @FXML
    private ScrollPane menu_scrollPane;
    
    @FXML
    private GridPane menu_gridPane;
    
    @FXML
    private TableView<Activite> menu_tableView;
    
    @FXML
    private TableColumn<Activite, String> menu_col_activiteName;
    
    @FXML
    private TableColumn<Activite, String> menu_col_quantity;
    
    @FXML
    private TableColumn<Activite, String> menu_col_price;
    
    @FXML
    private Label menu_total;
    
    @FXML
    private TextField menu_amount;
    
    @FXML
    private Label menu_change;
    
    @FXML
    private Button menu_payBtn;
    
    @FXML
    private Button menu_removeBtn;
    
    @FXML
    private Button menu_receiptBtn;
    
    @FXML
    private AnchorPane dashboard_form;
    
    @FXML
    private AnchorPane customers_form;
    
    @FXML
    private TableView<Adherent> customers_tableView;
    
    @FXML
    private TableColumn<customersData, String> customers_col_customerID;
    
    @FXML
    private TableColumn<customersData, String> customers_col_total;
    
    @FXML
    private TableColumn<customersData, String> customers_col_date;
    
    @FXML
    private TableColumn<customersData, String> customers_col_cashier;
    
    @FXML
    private Label dashboard_NC;
    
    @FXML
    private Label dashboard_TI;
    
    @FXML
    private Label dashboard_TotalI;
    
    @FXML
    private Label dashboard_NSP;
    
    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;
    
    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;
    
    private Alert alert;
    
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    private ObservableList<Activite> cardListData = FXCollections.observableArrayList();
    
    public void dashboardDisplayNC() {
        
        String sql = "SELECT COUNT(EntraineurID) FROM entraineur";
        connect = database.connectDB();
        
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

        String sql = "SELECT COUNT(ActiviteId) FROM activitedata";
        connect = database.connectDB();

        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nc = result.getInt("COUNT(ActiviteId)");
            }
            dashboard_TI.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardTotalI() {

        String sql = "SELECT COUNT(certificationId) FROM certification";
        connect = database.connectDB();

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
        
        connect = database.connectDB();
        
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
    
    public void dashboardIncomeChart() {
        dashboard_incomeChart.getData().clear();
        
        String sql = "SELECT date, SUM(total) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
            }
            
            dashboard_incomeChart.getData().add(chart);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void dashboardCustomerChart(){
        dashboard_CustomerChart.getData().clear();
        
        String sql = "SELECT date, COUNT(id) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = database.connectDB();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }
            
            dashboard_CustomerChart.getData().add(chart);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void inventoryAddBtn() {

        if (inventory_activiteID.getText().isEmpty()
                || inventory_activiteName.getText().isEmpty()
                || data.path == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            // CHECK activite ID
            String checkProdID = "SELECT activiteid FROM activitedata WHERE activiteid = '"
                    + inventory_activiteID.getText() + "'";

            connect = database.connectDB();




            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(inventory_activiteID.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO activitedata "
                            + "(activiteId, activiteName, image) "
                            + "VALUES(?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, inventory_activiteID.getText());
                    prepare.setString(2, inventory_activiteName.getText());


                    String path = data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(3, path);

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

        if (inventory_activiteID.getText().isEmpty()
                || inventory_activiteName.getText().isEmpty()
                || data.path == null || data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE activitedata SET "
                    + "activiteId = '" + inventory_activiteID.getText() + "', activiteName = '"
                    + inventory_activiteName.getText() + "', image = '"
                    + path  + "' WHERE id = " + data.id;

            connect = database.connectDB();

            try {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE activite ID: " + inventory_activiteID.getText() + "?");
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
        if (data.id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE activite ID: " + inventory_activiteID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM activitedata WHERE id = " + data.id;
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

        inventory_activiteID.setText("");
        inventory_activiteName.setText("");
        data.path = "";
        data.id = 0;
        inventory_imageView.setImage(null);

    }

    // LETS MAKE A BEHAVIOR FOR IMPORT BTN FIRST
    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }

    // MERGE ALL DATAS
    public ObservableList<Activite> inventoryDataList() {

        ObservableList<Activite> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM activitedata";

        connect = database.connectDB();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Activite prodData;

            while (result.next()) {

                prodData = new Activite(result.getInt("id"),
                        result.getString("activiteId"),
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

        inventory_col_activiteID.setCellValueFactory(new PropertyValueFactory<>("activiteId"));
        inventory_col_activiteName.setCellValueFactory(new PropertyValueFactory<>("activiteName"));

        inventory_tableView.setItems(inventoryListData);

    }

    public void inventorySelectData() {

        Activite prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        inventory_activiteID.setText(prodData.getActiviteId());
        inventory_activiteName.setText(prodData.getActiviteName());


        data.path = prodData.getImage();

        String path = "File:" + prodData.getImage();

        data.id = prodData.getId();

        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }








    private int cID;

    public void customerID() {

        String sql = "SELECT MAX(AdherentId) FROM adherent";
        connect = database.connectDB();

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

            data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Adherent> customersDataList() {

        ObservableList<Adherent> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM adherent";
        connect = database.connectDB();

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

    public void customersShowData() {
        customersListData = customersDataList();

        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("adherentID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("nom"));


        customers_tableView.setItems(customersListData);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);
//            menu_form.setVisible(false);
//            customers_form.setVisible(false);

            dashboardDisplayNC();
            dashboardDisplayTI();
            dashboardTotalI();
//            dashboardNSP();
//            dashboardIncomeChart();
//            dashboardCustomerChart();

        } else if (event.getSource() == inventory_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
//            menu_form.setVisible(false);
//            customers_form.setVisible(false);

            inventoryShowData();
        } else if (event.getSource() == menu_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
//            menu_form.setVisible(true);
//            customers_form.setVisible(false);

//            menuDisplayCard();
//            menuDisplayTotal();
//            menuShowOrderData();
        } else if (event.getSource() == customers_btn) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            menu_form.setVisible(false);
            customers_form.setVisible(true);

            customersShowData();
        }

    }
// LETS PROCEED TO OUR DASHBOARD FORM : )

//    public void logout() {
//
//        try {
//
//            alert = new Alert(AlertType.CONFIRMATION);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Are you sure you want to logout?");
//            Optional<ButtonType> option = alert.showAndWait();
//
//            if (option.get().equals(ButtonType.OK)) {
//
//                // TO HIDE MAIN FORM
//                logout_btn.getScene().getWindow().hide();
//
//                // LINK YOUR LOGIN FORM AND SHOW IT
//                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//
//                stage.setTitle("Cafe Shop Management System");
//
//                stage.setScene(scene);
//                stage.show();
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    public void displayUsername() {

        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
//        displayUsername();
        
        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardTotalI();
//        dashboardNSP();
//        dashboardIncomeChart();
//        dashboardCustomerChart();

        inventoryShowData();
        
//        menuDisplayCard();
//        menuGetOrder();
//        menuDisplayTotal();
//        menuShowOrderData();
        
        customersShowData();
        
    }
    
}

