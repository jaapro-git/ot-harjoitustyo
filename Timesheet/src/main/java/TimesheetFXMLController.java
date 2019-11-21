/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jukka
 */
import timesheet.domain.TimesheetService;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.fxml.FXML;

import javafx.stage.Window;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
 
public class TimesheetFXMLController {
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnLogout;
     
 
    @FXML
    private AnchorPane root;
     
    @FXML
    private Label lblUsername;
    @FXML
    private Label lblEntries;
    @FXML
    private TableView<?> tblEntries;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colUser;
    @FXML
    private TableColumn<?, ?> colComment;
    @FXML
    private TableColumn<?, ?> colComplete;
    @FXML
    private TableColumn<?, ?> colBeginStamp;
    @FXML
    private TableColumn<?, ?> colEndStamp;
    @FXML
    private TableColumn<?, ?> colRemove;
    @FXML
    private Label lblComment;
    @FXML
    private TextField txtComment;
    @FXML
    private Button btnStart;
     
    // Add a public no-args constructor
    public TimesheetFXMLController(){
    }
     
    @FXML
    private void doLogin(ActionEvent event){
        System.out.println("Test");
        Window owner = btnLogin.getScene().getWindow();
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Test");
        alert.setHeaderText(null);
        alert.setContentText("Test");
        alert.initOwner(owner);
        alert.showAndWait(); 
    }

    @FXML
    private void doLogout(ActionEvent event) {
        System.out.println("Test");
    }
}
