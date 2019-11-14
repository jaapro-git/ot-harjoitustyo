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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Jukka
 */
public class DbUserDao implements UserDao{
    
    private List<User> users;
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createUsersTable;
    String url;
    
    public DbUserDao() throws Exception{
        url = "jdbc:sqlite:db/timesheetUsers.db";
        
        createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                        + " uname text PRIMARY KEY, \n"
                        + " name text NOT NULL \n"
                        + ");";
           
        try{
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                Statement stmt = conn.createStatement();
                stmt.execute(createUsersTable);
                
                conn.close();
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    //private void update() throws Exception{
    //    
    //}
    
    @Override
    public List<User> getUsers(){
        
        String selectAllUsers = "SELECT uname, name "
                                + "FROM users;";
                
        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectAllUsers);
                
                while(rs.next()){
                    users.add(new User(rs.getString("uname"), rs.getString("name")));
                }
                
                conn.close();
            }                              
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    @Override
    public User findByUname(String uname){
        
        String selectCurrentUser = "SELECT uname, name "
                                + "FROM users "
                                + "WHERE uname = ?;";
                
        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                PreparedStatement pstmt = conn.prepareStatement(selectCurrentUser);
                pstmt.setString(1, uname);
                ResultSet rs = pstmt.executeQuery();
                
                if(rs.first()) return new User(rs.getString("uname"), rs.getString("name"));
                
                conn.close();
            }                              
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        return null;
    }
    
    @Override
    public boolean create(User user) throws Exception{
             
        String createUser = "INSERT INTO TABLE users (uname, name) "
                                + "VALUES(?, ?);";
                
        try{
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                PreparedStatement pstmt = conn.prepareStatement(createUser);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getName());
                pstmt.executeUpdate();
                
                conn.close();
                return true;
            }                              
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }
}
