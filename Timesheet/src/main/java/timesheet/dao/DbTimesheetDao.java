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
    
    /**
     * Creates the timesheet entry db table if it does not exist and refreshes it with data.
     * @param debug
     * @throws Exception
     */
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
           
        prepareConnection();
        if (conn != null) {               
            Statement stmt = conn.createStatement();
            stmt.execute(createTimesheetEntriesTable);
            conn.close();
        } 
        refreshEntries();
    } 
    
    private void refreshEntries() throws Exception {
        
        if (entries == null) {
            entries = new ArrayList<>();
        }
        
        prepareConnection();
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
    } 
    
    private void update() throws Exception {
        prepareConnection();
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
    } 
    
    private int generateId()  {
        if (entries.isEmpty()) {
            return 1;
        } else {
            return entries.get(entries.size() - 1).getId() + 1;
        } 
    } 
    
    /**
     *
     * @return
     */
    @Override
    public List<TimesheetEntry> getEntries() {
        return entries;
    } 

    /**
     * Creates a new entry in the database and returns the new id.
     * @param entry
     * @return
     * @throws Exception
     */
    @Override
    public int create(TimesheetEntry entry) throws Exception  {
        int id = generateId();
        entry.setId(id);
        entries.add(entry);
        update();
        return id;
    } 
    
    /**
     * Deletes an entry from the database
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public boolean delete(int id) throws Exception  
    {
        prepareConnection();
        if (conn != null) {
            PreparedStatement pstmt = conn.prepareStatement(deleteTimesheetEntry);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.close();
            entries.removeIf(e -> e.getId() == id);
        }     
        
        return false;
    } 

    /**
     * Sets an antry as completed and updates the information in the database
     * @param id
     * @throws Exception
     */
    @Override
    public void setComplete(int id) throws Exception  {
        for (TimesheetEntry e : entries)  {
            if  (e.getId()  ==  id)  {
                e.setComplete();
            } 
        } 
        update();
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