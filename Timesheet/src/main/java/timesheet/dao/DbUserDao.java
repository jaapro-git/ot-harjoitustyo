/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import java.util.ArrayList;
import java.util.List;
import timesheet.domain.User;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jukka
 */
public class DbUserDao implements UserDao{
    
    private List<User> users;
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createUsersTable;
    
    public DbUserDao() throws Exception{
        String url = "jdbc:sqlite:../db/timesheetUsers.db";
        
        createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                        + " uname text PRIMARY KEY, \n"
                        + " name text NOT NULL \n"
                        + ");";
        
        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){
                meta = conn.getMetaData();
                Statement stmt = conn.createStatement();
                stmt.execute(createUsersTable);
            }
        } catch(SQLException ex){
            
        }
    }
    
    private void update() throws Exception{
        
    }
    
    @Override
    public List<User> getUsers(){
        return new ArrayList<>();
    }
    
    @Override
    public User findByUname(String uname){
        return new User("test", "Test");
    }
    
    @Override
    public User create(User user) throws Exception{
        return new User("test", "Test");
    }
}
