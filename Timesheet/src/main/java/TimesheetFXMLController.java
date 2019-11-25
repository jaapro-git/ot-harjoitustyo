

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
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<?> tblEntries;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colUsername;
    @FXML
    private TableColumn<?, ?> colComment;
    @FXML
    private TableColumn<?, ?> colComplete;
    @FXML
    private TableColumn<?, ?> colBeginTime;
    @FXML
    private TableColumn<?, ?> colEndTime;
    @FXML
    private TableColumn<?, ?> colRemove;
    @FXML
    private Label lblComment;
    @FXML
    private TextField txtComment;
    @FXML
    private Button btnStart;
    @FXML
    private VBox vboxTimesheet;
     
    // Add a public no-args constructor
    public TimesheetFXMLController() {
        
    } 
    
    public void initialize() throws Exception {
        DbUserDao userData = new DbUserDao(false);
        DbTimesheetDao timesheetData = new DbTimesheetDao(false);
        timesheetService = new TimesheetService(timesheetData, userData);
        btnLogout.setVisible(false);
        vboxTimesheet.setVisible(false);
        
        //bind table columns
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        colComplete.setCellValueFactory(new PropertyValueFactory<>("complete"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colBeginTime.setCellValueFactory(new PropertyValueFactory<>("beginTimeStamp"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTimeStamp"));
    } 
     
    @FXML
    private void doLogin(ActionEvent event) {
        
        if (txtUsername.getText().equals("")) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Missing Username");
            alert.setContentText("Please enter a username");
            alert.show();
            vboxTimesheet.setVisible(false);
            return;
        } 
        
        if (timesheetService.userLogin(txtUsername.getText())) {
            System.out.println("User " + txtUsername.getText() + " successully logged in!");
            btnLogin.setVisible(false);
            btnLogout.setVisible(true);
            txtUsername.setDisable(true);
        } else if (timesheetService.newUser(txtUsername.getText(), txtUsername.getText().toUpperCase())) {
            System.out.println("New user " + txtUsername.getText() + " successully created!");
        } else {
            System.out.println("Something did not work!");
            return;
        } 

        lblEntries.setText(lblEntries.getText().replace("$cUser$", timesheetService.getCurrentUser().getName()));
        refreshTimesheetTable();
        vboxTimesheet.setVisible(true);
    } 

    @FXML
    private void doLogout(ActionEvent event) {
        txtUsername.setDisable(false);
        btnLogin.setVisible(true);
        btnLogout.setVisible(false);
        vboxTimesheet.setVisible(false);
    } 
    
    private void refreshTimesheetTable() {
        
        ObservableList<TimesheetEntry> entryData = FXCollections.observableArrayList();
        
        List entries = timesheetService.getEntries();
        
        if (entries.size() != 0) {
            entryData.addAll(entries);
            tblEntries.getItems().setAll(entries);
        } 
    } 
    
    @FXML
    private void doAddEntry() {
        System.out.println("add entry");
        timesheetService.createTimeSheetEntry(txtComment.getText());
        txtComment.clear();
        refreshTimesheetTable();
    } 
} 