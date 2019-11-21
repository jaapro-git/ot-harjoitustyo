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
import java.util.Optional;
 
import javafx.fxml.FXML;

import javafx.stage.Window;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import timesheet.dao.DbTimesheetDao;
import timesheet.dao.DbUserDao;
 
public class TimesheetFXMLController {
    
    private TimesheetService timesheetService;
    
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
    public void initialize() throws Exception{
        DbUserDao userData = new DbUserDao();
        DbTimesheetDao timesheetData = new DbTimesheetDao();
        timesheetService = new TimesheetService(timesheetData, userData);
        btnLogout.setVisible(false);
    }
     
    @FXML
    private void doLogin(ActionEvent event){
        
        if(txtUsername.getText().equals("")){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Missing Username");
            alert.setContentText("Please enter a username");
            alert.show();
            return;
        }
        
        if(timesheetService.userLogin(txtUsername.getText())){
                System.out.println("User "+txtUsername.getText()+" successully logged in!");
                btnLogin.setVisible(false);
                btnLogout.setVisible(true);
                txtUsername.setDisable(true);
        }else if(timesheetService.newUser(txtUsername.getText(), txtUsername.getText())){
                System.out.println("New user "+txtUsername.getText()+" successully created!");
        }else{
                System.out.println("Something did not work!");
        }  
    }

    @FXML
    private void doLogout(ActionEvent event) {
        txtUsername.setDisable(false);
        btnLogin.setVisible(true);
        btnLogout.setVisible(false);
    }
}