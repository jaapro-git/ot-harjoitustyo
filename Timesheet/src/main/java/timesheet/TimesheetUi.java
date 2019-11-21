/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet;

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


public class TimesheetUi extends Application{
    private TimesheetService timesheetService;

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    @Override
    public void init() throws Exception {
        //Properties properties = new Properties();

        //properties.load(new FileInputStream("config.properties"));
        
        //String userFile = properties.getProperty("userFile");
        //String todoFile = properties.getProperty("todoFile");
            
        DbUserDao userData = new DbUserDao();
        DbTimesheetDao timesheetData = new DbTimesheetDao();
        timesheetService = new TimesheetService(timesheetData, userData);
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
        } catch(Exception e){
            e.printStackTrace();
            stop();
        }
    }
    
    @Override
    public void stop(){
        
    }
}
