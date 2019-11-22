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

/**
 *
 * @author Jukka
 */
public class DbTimesheetDao implements TimesheetDao{
    
    private List<TimesheetEntry> entries;
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createTimesheetEntriesTable;
    final private String getAllTimesheetEntries;
    final private String createTimesheetEntry;
    final private String url;
    
    public DbTimesheetDao() throws Exception{
        
        url = "jdbc:sqlite:timesheetTimesheetEntries.db";
        
        createTimesheetEntriesTable = "CREATE TABLE IF NOT EXISTS timesheetentries (\n"
                                    + "id integer PRIMARY KEY, \n"
                                    + "comment text, \n"
                                    + "complete integer NOT NULL, \n"
                                    + "uname text NOT NULL, \n"
                                    + "begin text NOT NULL, \n"
                                    + "end text \n"
                                    + ");";
        
        getAllTimesheetEntries = "SELECT id, comment, complete, uname, begin, end "
                               + "FROM timesheetentries;";
        
        createTimesheetEntry = "INSERT INTO timesheetentries (id, comment, complete, uname, begin, end) "
                                    + "VALUES(?, ?, ?, ?, ?, ?);";
           
        try{
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                Statement stmt = conn.createStatement();
                stmt.execute(createTimesheetEntriesTable);
                conn.close();
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        refreshEntries();
    }
    
    private void refreshEntries() throws Exception{
        
        if(entries==null) entries = new ArrayList<>();
        
        try{
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getAllTimesheetEntries);
                
                while(rs.next()){
                    entries.add(new TimesheetEntry(rs.getInt("id"),
                                                   rs.getString("comment"),
                                                   rs.getBoolean("complete"),
                                                   rs.getString("uname"),
                                                   rs.getString("begin"),
                                                   rs.getString("end")));
                }
                conn.close();
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private void update() throws Exception{
        try{
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if(conn != null){
                for(TimesheetEntry entry:entries){
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
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    private int generateId() {
        if(entries.isEmpty()){
            return 1;
        } else{
            return entries.size() + 1;
        }
    }
    
    private boolean deleteEntry(int id) {
        return false;
    }
    
    @Override
    public List<TimesheetEntry> getEntries(){
        return entries;
    }

    @Override
    public boolean create(TimesheetEntry entry) throws Exception {
        entry.setId(generateId());
        entries.add(entry);
        update();
        return false;
    }

    @Override
    public void setComplete(int id) throws Exception {
        for (TimesheetEntry e : entries) {
            if (e.getId() == id) {
                e.setComplete();
            }
        }
        update();
    }
}
