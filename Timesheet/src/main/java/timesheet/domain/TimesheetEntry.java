/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

import java.sql.Time;
import java.time.Instant;

/**
 *
 * @author Jukka
 */
public class TimesheetEntry extends Object{
    
    private int id;
    private String comment;
    private boolean complete;
    private String username;
    private Instant beginTimeStamp;
    private Instant endTimeStamp;
      
    public TimesheetEntry(int id, String comment, boolean complete, String user, String beginTime, String endTime){
        this.id = id;
        this.comment = comment;
        this.complete = complete;
        this.username = user;
        this.beginTimeStamp = Instant.parse(beginTime);
        this.endTimeStamp = Instant.parse(endTime);
    }
    
    public TimesheetEntry(String user, String comment){
        this.id = 123;
        this.comment = comment;
        this.complete = false;
        this.username = user;
        this.beginTimeStamp = Instant.now();
        this.endTimeStamp = Instant.MAX;
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
    
    public String getUsername(){
        return this.username;
    }
    
    public String getBeginTime(){
        return this.beginTimeStamp.toString();
    }
        
    public String getEndTime(){
        return this.endTimeStamp.toString();
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
