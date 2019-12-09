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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
 
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
    @FXML
    private Button btnExport;
    @FXML
    private MenuItem menuExit;

     
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

    @FXML
    private void doExport() {
        
        // Create a Workbook
        Workbook workbook = new XSSFWorkbook();
        // Create a Sheet
        Sheet sheet = workbook.createSheet("timesheet");
        
        // Create a header row
        Row headerRow = sheet.createRow(0);    
        ///Set titles of columns
        for (int i=0; i<tblEntries.getColumns().size();i++) {
            headerRow.createCell(i).setCellValue(tblEntries.getColumns().get(i).getText());
        }
        
        //Write entries data
        for (int rNum = 0; rNum < tblEntries.getItems().size(); rNum++) {
            
            Row row = sheet.createRow(rNum + 1);
            for (int cNum = 0; cNum < tblEntries.getColumns().size(); cNum++) {
                Object cellValue = tblEntries.getColumns().get(cNum).getCellObservableValue(rNum).getValue();

                try {
                    if (cellValue != null && Double.parseDouble(cellValue.toString()) != 0.0) {
                        row.createCell(cNum).setCellValue(Double.parseDouble(cellValue.toString()));
                    }
                } catch (  NumberFormatException e ){
                    row.createCell(cNum).setCellValue(cellValue.toString());
                }
            }
        }
        
        try {
            //String formatted = DateTimeFormatter.ofPattern("yyyyMMddkkmmss",Locale.GERMAN).format(Instant.now());
            FileOutputStream fileOut = new FileOutputStream("timesheet_export_"+Long.toString(Instant.now().getEpochSecond())+".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Timesheet Successfully Exported");
            alert.setHeaderText("A new file was created in the root folder");
            alert.show();
        } catch (IOException ex) {
            
        }

    }

    @FXML
    private void doExit() {
        Window window = root.getScene().getWindow();  // Get the primary stage from your Application class

        window.fireEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    
} 