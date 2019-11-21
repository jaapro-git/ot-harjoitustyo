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
    
    public TimesheetService(TimesheetDao timesheetData, UserDao userData){
        
        //this.cUser = cUser;
        this.timesheetData = timesheetData;
        this.userData = userData;
    }
    
    public boolean createTimeSheetEntry(){
        TimesheetEntry entry = new TimesheetEntry(cUser);
        
        try{
            timesheetData.create(entry);
        } catch(Exception ex){
            return false;
        }
        return true;
    }
    
    public List<TimesheetEntry> getEntries(){
       if(cUser == null) return new ArrayList<>();
       
       return timesheetData.getEntries();
    }    
    
    public void setComplete(int id){
        try{
            timesheetData.setComplete(id);
        } catch(Exception ex){
        }    
    }
        
    public boolean userLogin(String uname){
        User user = userData.findByUname(uname);
        
        if(user == null) return false;
        
        cUser = user;
   
        return true;
    }
    
    public User getCurrentUser(){
        return cUser;
    }
    
    public void userLogout(){
        cUser = null;
    }
    
    public boolean newUser(String uname, String name){
        
        //already exists?
        if(userData.findByUname(uname)!=null) return false;

        User user = new User(uname, name);
        
        try{
            userData.create(user);
        }catch (Exception ex){
            return false;
        }
        
        return true;
    }
}
