/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jukka
 */
public class Database {
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createTimesheetEntriesTable;
    final private String createUsersTable;
    final private String url;
    
    /**
     * Creates the timesheet entry and users db tables if they do not exist.
     * @param debug
     * @throws Exception
     */
    public Database(boolean debug) throws Exception {
        
        if (debug) {
            url = "jdbc:sqlite::memory:";
        } else {
            url = "jdbc:sqlite:timesheetDatabase.db";
        } 
        
        createTimesheetEntriesTable = "CREATE TABLE if  NOT EXISTS timesheetentries (\n"
                                    + "id integer PRIMARY KEY, \n"
                                    + "comment text, \n"
                                    + "complete integer NOT NULL, \n"
                                    + "uname text NOT NULL, \n"
                                    + "begin text NOT NULL, \n"
                                    + "end text \n"
                                    + ");";
        
        createUsersTable = "CREATE TABLE IF NOT EXISTS users (\n"
                 + " uname text PRIMARY KEY, \n"
                 + " name text NOT NULL \n"
                 + ");";
           
        prepareConnection();
        if (conn != null) {               
            Statement stmt = conn.createStatement();
            stmt.execute(createTimesheetEntriesTable);
            stmt.execute(createUsersTable);
            conn.close();
        } 
    } 
    
    private void prepareConnection() throws Exception {
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }
} 