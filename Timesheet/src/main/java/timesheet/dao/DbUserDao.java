/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import java.util.ArrayList;
import java.util.List;
import timesheet.domain.User;

/**
 *
 * @author Jukka
 */
public class DbUserDao implements UserDao{
    
    private List<User> users;
    private String file;
    
    public DbUserDao() throws Exception{
        
    }
    
    private void update() throws Exception{
        
    }
    
    @Override
    public List<User> getUsers(){
        
    }
    
    @Override
    public User findByUname(String uname){
        
    }
    
    @Override
    public User create(User user) throws Exception{
        
    }
}
