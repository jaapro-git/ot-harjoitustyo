/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet;

import timesheet.dao.Database;
import timesheet.ui.*;

/**
 *
 * @author Jukka
 */

public class Main {
    
    public static void main(String[] args) {
        
        try {
            Database db = new Database(false);
        } catch (Exception ex) {
            
        }
                
        timesheet.ui.TimesheetUi.main(args);

        
    }
}
