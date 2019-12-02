package timesheet.ui;







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

import java.util.List;
import java.util.Optional;
import javafx.collections.ObservableList;
 
import javafx.fxml.FXML;

import javafx.collections.FXCollections;

import javafx.event.ActionEvent;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import timesheet.dao.DbTimesheetDao;
import timesheet.dao.DbUserDao;
import timesheet.domain.TimesheetEntry;
 
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
    private TableView<TimesheetEntry> tblEntries;
    @FXML
    private TableColumn<TimesheetEntry, Integer> colId;
    @FXML
    private TableColumn<TimesheetEntry, String> colUsername;
    @FXML
    private TableColumn<TimesheetEntry, String> colComment;
    @FXML
    private TableColumn<TimesheetEntry, Boolean> colComplete;
    @FXML
    private TableColumn<TimesheetEntry, String> colBeginTime;
    @FXML
    private TableColumn<TimesheetEntry, String> colEndTime;
    @FXML
    private TableColumn<?, ?> colTotalTime;
    @FXML
    private Label lblComment;
    @FXML
    private TextField txtComment;
    @FXML
    private Button btnStart;
    @FXML
    private VBox vboxTimesheet;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnComplete;
    @FXML
    private VBox vboxAdd;

     
    // Add a public no-args constructor
    public TimesheetFXMLController() {
        
    } 
    
    public void initialize() throws Exception {
        DbUserDao userData = new DbUserDao(false);
        DbTimesheetDao timesheetData = new DbTimesheetDao(false);
        timesheetService = new TimesheetService(timesheetData, userData);
        btnLogout.setVisible(false);
        vboxTimesheet.setVisible(false);
        vboxAdd.setVisible(false);
    } 
     
    @FXML
    private void doLogin() {
        
        if (txtUsername.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Missing Username");
            alert.setHeaderText("Please enter a username");
            alert.show();
            vboxTimesheet.setVisible(false);
            return;
        } 
        
        if (timesheetService.userLogin(txtUsername.getText())) {
            btnLogin.setVisible(false);
            btnLogout.setVisible(true);
            txtUsername.setDisable(true);
        } else {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("User does not exist");
            dialog.setHeaderText("The specified user does not exist. Would you like to create it?");
            dialog.setContentText("Name for the new user");
            dialog.showAndWait();
            
            if (dialog.getResult() == null  || dialog.getResult().equals("")) {
                return;
            } 
            
            if (timesheetService.newUser(txtUsername.getText(), dialog.getResult())) {
                
                if (timesheetService.userLogin(txtUsername.getText())) {
                    btnLogin.setVisible(false);
                    btnLogout.setVisible(true);
                    txtUsername.setDisable(true);
                }
            } else {
                System.out.println("Something did not work!");
                return;
            } 
        }

        lblEntries.setText(lblEntries.getText().replace("$cUser$", timesheetService.getCurrentUser().getName()));
        refreshTimesheetTable();
        vboxTimesheet.setVisible(true);
    } 

    @FXML
    private void doLogout() throws Exception {
        txtUsername.setDisable(false);
        btnLogin.setVisible(true);
        btnLogout.setVisible(false);
        vboxTimesheet.setVisible(false);
        
        lblEntries.setText(lblEntries.getText().replace(timesheetService.getCurrentUser().getName(), "$cUser$"));
        timesheetService.userLogout();
        initialize();
    } 
    
    private void refreshTimesheetTable() {
        
        List entries = timesheetService.getEntries();
        ObservableList entryData = FXCollections.observableArrayList(entries);
        
        if (entries.size() != 0) {
            
            tblEntries.getItems().clear();
            tblEntries.getItems().setAll(entryData);
        } 
    } 
    
    @FXML
    private void doAddEntry() {
        timesheetService.createTimeSheetEntry(txtComment.getText());
        txtComment.clear();
        refreshTimesheetTable();
        vboxAdd.setVisible(false);
    } 

    @FXML
    private void doAddRow() {
        vboxAdd.setVisible(!vboxAdd.isVisible());
    }

    @FXML
    private void doDeleteRow() {
        TimesheetEntry entry = tblEntries.getSelectionModel().getSelectedItem();
        
        if (entry != null) {
            timesheetService.deleteTimeSheetEntry(entry.getId());
            refreshTimesheetTable();
        }
    }

    @FXML
    private void doCompleteRow() {
        TimesheetEntry entry = tblEntries.getSelectionModel().getSelectedItem();
        
        if (entry != null) {
            timesheetService.setComplete(entry.getId());
            refreshTimesheetTable();
        }
    }
} 