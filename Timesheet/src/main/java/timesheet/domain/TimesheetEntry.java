/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

/**
 *
 * @author Jukka
 */
public class TimesheetEntry {
    
    private int id;
    private String comment;
    private boolean complete;
    private User user;
    
    public TimesheetEntry(int id, String comment, boolean complete, User user){
        this.id = id;
        this.comment = comment;
        this.complete complete;
        this.user = user;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public int getId(){
        return this.id;
    }
    
    public boolean getComplete(){
        return this.complete;
    }
    
    public User getUser(){
        return this.user;
    }
    
    public void setComplete(){
        this.complete = true;
    }
    
    
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof TimesheetEntry)) {
            return false;
        }

        TimesheetEntry other = (TimesheetEntry) obj;
        return id == other.id;

    }
}
