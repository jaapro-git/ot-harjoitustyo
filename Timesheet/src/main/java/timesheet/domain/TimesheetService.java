/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

import timesheet.dao.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Jukka
 */
public class TimesheetService {
    
    private User cUser;
    private UserDao userData;
    private TimesheetDao timesheetData;
    
    public TimesheetService(TimesheetDao timesheetData, UserDao userData) {
        
        //this.cUser = cUser;
        this.timesheetData = timesheetData;
        this.userData = userData;
    } 
    
    public boolean createTimeSheetEntry(String comment) {
        TimesheetEntry entry = new TimesheetEntry(cUser.getUsername(), comment);
        
        try {
            timesheetData.create(entry);
        } catch (Exception ex) {
            return false;
        } 
        return true;
    } 
    
    public List<TimesheetEntry> getEntries() {
        if (cUser == null) {
            return new ArrayList<>();
        }
       
        List<TimesheetEntry> entries = timesheetData.getEntries();
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getUsername() != this.cUser.getUsername()) {
                entries.remove(i);
            } 
        } 
        return entries;
    }     
    
    public void setComplete(int id) {
        try {
            timesheetData.setComplete(id);
        } catch (Exception ex) {
			
        }     
    } 
        
    public boolean userLogin(String uname) {
        User user = userData.findByUname(uname);
        
        if (user == null) {
            return false;
        }
        
        this.cUser = user;
   
        return true;
    } 
    
    public User getCurrentUser() {
        return cUser;
    } 
    
    public void userLogout() {
        cUser = null;
    } 
    
    public boolean newUser(String uname, String name) {
        
        //already exists?
        if (userData.findByUname(uname) != null) {
            return false;
        }

        User user = new User(uname, name);
        
        try {
            if (userData.create(user)) {
                this.cUser = user;
            }
        } catch (Exception ex) {
            return false;
        } 
        
        return true;
    } 
} 
