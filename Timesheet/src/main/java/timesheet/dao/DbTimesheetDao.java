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
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jukka
 */
public class DbTimesheetDao implements TimesheetDao{
    
    private List<TimesheetEntry> entries;
    
    private Connection conn;
    private DatabaseMetaData meta;
    final private String createTimesheetEntriesTable;
    
    public DbTimesheetDao() throws Exception{
        
        String url = "jdbc:sqlite:timesheetTimesheetEntries.db";
        
        createTimesheetEntriesTable = "CREATE TABLE IF NOT EXISTS timesheetentries (\n"
                                    + "id integer PRIMARY KEY, \n"
                                    + "comment text, \n"
                                    + "complete integer NOT NULL, \n"
                                    + "uname text NOT NULL, \n"
                                    + "begin text NOT NULL, \n"
                                    + "end text \n"
                                    + ");";
           
        try{
            // register the driver 
            String sDriverName = "org.sqlite.JDBC";
            Class.forName(sDriverName);
            conn = DriverManager.getConnection(url);
            if(conn != null){               
                Statement stmt = conn.createStatement();
                stmt.execute(createTimesheetEntriesTable);
            }
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        } finally{
            conn.close();
        }
    }
    
//    private void update() throws Exception{
//        
//    }
    
    private int generateId() {
        return entries.size() + 1;
    }
    
    @Override
    public List<TimesheetEntry> getEntries() {
        return entries;
    }

    @Override
    public boolean create(TimesheetEntry entry) throws Exception {
        entry.setId(generateId());
        entries.add(entry);
        return false;
    }

    @Override
    public void setComplete(int id) throws Exception {
        for (TimesheetEntry e : entries) {
            if (e.getId() == id) {
                e.setComplete();
            }
        }
    }
}
