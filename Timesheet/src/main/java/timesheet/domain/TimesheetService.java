/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

import timesheet.dao.*;

/**
 *
 * @author Jukka
 */
public class TimesheetService {
    
    private User cUser;
    private UserDao userData;
    private TimesheetDao timesheetData;
    
    
    public TimesheetService(User cUser){
        
        this.cUser = cUser;
    }
    
    
    public boolean userLogin(User uname){
        User user = UserDao.findByUname(uname);
        
        if(user == null) return false;
        
        cUser = user;
   
        return true;
    }
    
    public void userLogout(){
        cUser = null;
    }
    
    public boolean newUser(String uname, String name){
        
        //already exists?
        if(UserDao.findByUname(uname)!=null) return false;

        User user = new User(uname, name);
        
        try{
            UserDao.create(user);
        }catch Exception e{
            return false;
        }
        
        return true;
        }
    }
}
