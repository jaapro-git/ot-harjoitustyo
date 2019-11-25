/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.net.URL;

import timesheet.dao.DbTimesheetDao;
import timesheet.dao.DbUserDao;
import timesheet.domain.TimesheetService;

/**
 *
 * @author Jukka
 */


public class TimesheetUi extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception {
           
    }

    @Override
    public void start(Stage primaryStage) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/ui/Timesheet.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Timesheet");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }
    
    @Override
    public void stop() {
        
    }
}
