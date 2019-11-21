/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

import java.time.Instant;

/**
 *
 * @author Jukka
 */
public class TimesheetEntry {
    
    private int id;
    private String comment;
    private boolean complete;
    private User user;
    private Instant beginTimeStamp;
    private Instant endTimeStamp;
      
    public TimesheetEntry(int id, String comment, boolean complete, User user){
        this.id = id;
        this.comment = comment;
        this.complete = complete;
        this.user = user;
        this.beginTimeStamp = Instant.now();
    }
    
    public TimesheetEntry(User user){
        this.id = 123;
        this.complete = false;
        this.user = user;
        this.beginTimeStamp = Instant.now();
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public void setId(int id){
        this.id = id;
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
        this.endTimeStamp = Instant.now();
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
