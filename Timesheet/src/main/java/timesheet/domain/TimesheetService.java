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
    
    private User cuser;
    private UserDao userData;
    private TimesheetDao timesheetData;
    
    
    public TimesheetService(User cuser){
        
        this.cuser = cuser;
    }
    
    
    public boolean userLogin(User uname){
        User user = UserDao.findByUname(uname);
        
        if(user == null) return false;
        
        cuser = user;
   
        return true;
    }
    
    public void userLogout(){
        cuser = null;
    }
    
    public boolean newUser(String uname, String name){
        
        //already exists?
        if(UserDao.findByUname()!=null) return false;

        User user = new User(uname, name);
        
        try{
            userDao.create(user);
        }catch Exception e{
            return false;
        }
        
        return true;
        }
    }
}
