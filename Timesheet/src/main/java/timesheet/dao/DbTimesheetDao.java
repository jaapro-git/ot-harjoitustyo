/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import java.util.ArrayList;
import java.util.List;
import timesheet.domain.TimesheetEntry;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Collections;

/**
 *
 * @author Jukka
 */
public class DbTimesheetDao implements TimesheetDao {
    
    private List<TimesheetEntry> entries;
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createTimesheetEntriesTable;
    final private String getAllTimesheetEntries;
    final private String createTimesheetEntry;
    final private String deleteTimesheetEntry;
    final private String url;
    
    public DbTimesheetDao(boolean debug) throws Exception {
        
        if (debug) {
            url = "jdbc:sqlite::memory:";
        } else {
            url = "jdbc:sqlite:timesheetTimesheetEntries.db";
        } 
        
        createTimesheetEntriesTable = "CREATE TABLE if  NOT EXISTS timesheetentries (\n"
                                    + "id integer PRIMARY KEY, \n"
                                    + "comment text, \n"
                                    + "complete integer NOT NULL, \n"
                                    + "uname text NOT NULL, \n"
                                    + "begin text NOT NULL, \n"
                                    + "end text \n"
                                    + ");";
        
        getAllTimesheetEntries = "SELECT id, comment, complete, uname, begin, end "
                               + "FROM timesheetentries;";
        
        deleteTimesheetEntry = "DELETE "
                             + "FROM timesheetentries "
                             + "WHERE id = ?";
        
        createTimesheetEntry = "INSERT OR REPLACE INTO timesheetentries (id, comment, complete, uname, begin, end) "
                                    + "VALUES(?, ?, ?, ?, ?, ?);";
           
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                Statement stmt = conn.createStatement();
                stmt.execute(createTimesheetEntriesTable);
                conn.close();
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        refreshEntries();
    } 
    
    private void refreshEntries() throws Exception {
        
        if (entries == null) {
            entries = new ArrayList<>();
        }
        
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if (conn != null) {               
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getAllTimesheetEntries);
                
                while (rs.next()) {
                    entries.add(new TimesheetEntry(rs.getInt("id"),
                                                   rs.getString("comment"),
                                                   rs.getBoolean("complete"),
                                                   rs.getString("uname"),
                                                   rs.getString("begin"),
                                                   rs.getString("end")));
                } 
                conn.close();
                Collections.sort(entries);
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    } 
    
    private void update() throws Exception {
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                for (TimesheetEntry entry:entries) {
                    PreparedStatement pstmt = conn.prepareStatement(createTimesheetEntry);
                    pstmt.setInt(1, entry.getId());
                    pstmt.setString(2, entry.getComment());
                    pstmt.setBoolean(3, entry.getComplete());
                    pstmt.setString(4, entry.getUsername());
                    pstmt.setString(5, entry.getBeginTime());
                    pstmt.setString(6, entry.getEndTime());
                    pstmt.executeUpdate();
                }  
                conn.close();
            }     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    } 
    
    private int generateId()  {
        if (entries.isEmpty()) {
            return 1;
        } else {
            return entries.get(entries.size() - 1).getId() + 1;
        } 
    } 
    
    @Override
    public List<TimesheetEntry> getEntries() {
        return entries;
    } 

    @Override
    public int create(TimesheetEntry entry) throws Exception  {
        int id = generateId();
        entry.setId(id);
        entries.add(entry);
        update();
        return id;
    } 
    
    @Override
    public boolean delete(int id) throws Exception  {
        try {
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement(deleteTimesheetEntry);
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                conn.close();
                entries.removeIf(e -> e.getId() == id);
            }     
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
        
        return false;
    } 

    @Override
    public void setComplete(int id) throws Exception  {
        for (TimesheetEntry e : entries)  {
            if  (e.getId()  ==  id)  {
                e.setComplete();
            } 
        } 
        update();
    } 
} 