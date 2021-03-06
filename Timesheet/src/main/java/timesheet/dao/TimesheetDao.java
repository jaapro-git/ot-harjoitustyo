/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import java.util.List;
import timesheet.domain.TimesheetEntry;

/**
 *
 * @author Jukka
 */
public interface TimesheetDao {
    
    int create(TimesheetEntry entry) throws Exception;
    
    List<TimesheetEntry> getEntries();
    
    void setComplete(int id) throws Exception;

    public boolean delete(int id) throws Exception;
    
}
