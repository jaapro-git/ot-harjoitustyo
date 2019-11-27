/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

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
public class DbUserDao implements UserDao {
    
    private List<User> users;
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createUsersTable;
    final private String url;
    final private String selectAllUsers;
    final private String selectCurrentUser;
    final private String createUser;
    
    public DbUserDao(boolean debug) throws Exception {
        
        if (debug) {
            url = "jdbc:sqlite::memory:";
        } else {
            url = "jdbc:sqlite:timesheetUsers.db";
        } 
        
        createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                         + " uname text PRIMARY KEY, \n"
                         + " name text NOT NULL \n"
                         + ");";
        
        selectAllUsers = "SELECT uname, name "
                              + "FROM users;";
        
        selectCurrentUser = "SELECT uname, name "
                                 + "FROM users "
                                 + "WHERE uname = ?;";
        
        createUser = "INSERT OR REPLACE INTO users (uname, name) "
                                + "VALUES(?, ?);";
           
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                Statement stmt = conn.createStatement();
                stmt.execute(createUsersTable);
                
                conn.close();
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    } 
    
    //private void update() throws Exception {
    //    
    //} 
    
    public User getSingleUser(String uname) {
        for (User u:users) {
            if (u.getUsername().equals(uname)) {
                return u;
            }
        } 
        return null;
    } 
    
    @Override
    public List<User> getUsers() {
                
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(selectAllUsers);
                
                while (rs.next()) {
                    users.add(new User(rs.getString("uname"), rs.getString("name")));
                } 
                
                conn.close();
            }                               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return null;
    } 
    
    @Override
    public User findByUname(String uname) {
        
        User user;
                
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                PreparedStatement pstmt = conn.prepareStatement(selectCurrentUser);
                pstmt.setString(1, uname);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    user = new User(rs.getString("uname"), rs.getString("name"));
                } else {
                    return null;
                } 
                conn.close();
                return user;
            }                               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return null;
    } 
    
    @Override
    public boolean create(User user) throws Exception {
                   
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                PreparedStatement pstmt = conn.prepareStatement(createUser);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getName());
                pstmt.executeUpdate();
                
                conn.close();
                return true;
            }                               
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        return false;
    } 
} 
