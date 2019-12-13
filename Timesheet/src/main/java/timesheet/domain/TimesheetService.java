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
    
    /**
     * Constructor
     * @param timesheetData     An instance of TimesheetDao
     * @param userData          An instance of UserDao
     */
    public TimesheetService(TimesheetDao timesheetData, UserDao userData) {
        
        this.timesheetData = timesheetData;
        this.userData = userData;
    } 
    
    /**
     * Creates a new timesheet entry from the UI.
     * @param comment           A comment for the new entry
     * @return
     */
    public int createTimeSheetEntry(String comment) {
        int id;
        
        TimesheetEntry entry = new TimesheetEntry(cUser.getUsername(), comment);
        
        try {
            id = timesheetData.create(entry);
        } catch (Exception ex) {
            return -1;
        } 
        return id;
    } 
    
    /**
     * Deletes an existing entry,
     * @param id                Id of the selected timesheet entry
     * @return
     */
    public boolean deleteTimeSheetEntry(int id) {
                
        try {
            timesheetData.delete(id);
        } catch (Exception ex) {
            return false;
        } 
        return true;
    } 
    
    /**
     * Refreshes the entry list from UI and filters it for the current user.
     * @return
     */
    public List<TimesheetEntry> getEntries() {
        if (cUser == null) {
            return new ArrayList<>();
        }
        
        List<TimesheetEntry> uiList = new ArrayList<TimesheetEntry>();
        List<TimesheetEntry> entries = timesheetData.getEntries();
        
        for (TimesheetEntry e:entries) {
            if (e.getUsername().equals(cUser.getUsername())) {
                uiList.add(e);
            }
        }
        return uiList;
    }     
    
    /**
     * Sets the selected entry as completed
     * @param id                Id of the selected entry
     */
    public void setComplete(int id) {
        try {
            timesheetData.setComplete(id);
        } catch (Exception ex) {
			
        }     
    } 
        
    /**
     * Initiate a login for the given user
     * @param uname                 Username
     * @return
     */
    public boolean userLogin(String uname) {
        User user = null;
        
        try {
            user = userData.findByUname(uname);
        } catch (Exception ex) {
			
        }  
        
        if (user == null) {
            return false;
        }
        
        this.cUser = user;
   
        return true;
    } 
    
    /**
     *
     * @return
     */
    public User getCurrentUser() {
        return cUser;
    } 
    
    /**
     * Initializes the controller instance
     */
    public void userLogout() {
        this.cUser = null;
        this.userData = null;
        this.timesheetData = null;
    } 
    
    /**
     * Creates a new users in the database
     * @param uname                 Username
     * @param name                  Real name
     * @return
     */
    public boolean newUser(String uname, String name) {
        
        //already exists?
        try{
            if (userData.findByUname(uname) != null) {
                return false;
            }
        } catch (Exception ex) {
			
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
