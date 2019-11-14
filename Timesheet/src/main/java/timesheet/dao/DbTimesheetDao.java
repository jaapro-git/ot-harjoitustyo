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
    
    private void update() throws Exception{
        
    }
    
    private int generateId() {
        return entries.size() + 1;
    }
    
    @Override
    public List<TimesheetEntry> getEntries() {
        return entries;
    }

    @Override
    public TimesheetEntry create(TimesheetEntry entry) throws Exception {
        entry.setId(generateId());
        entries.add(entry);
        update();
        return entry;
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
