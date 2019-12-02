/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.domain;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 *
 * @author Jukka
 */
public class TimesheetEntry extends Object implements Comparable<TimesheetEntry> {
    
    private int id;
    private String comment;
    private boolean complete;
    private String username;
    private Instant beginTimeStamp;
    private Instant endTimeStamp;
    private long totalTime;
      
    public TimesheetEntry(int id, String comment, boolean complete, String user, String beginTime, String endTime) {
        this.id = id;
        this.comment = comment;
        this.complete = complete;
        this.username = user;
        this.beginTimeStamp = Instant.parse(beginTime);
        
        if (endTime.equals("")) {
            endTimeStamp = null;
        } else {
            this.endTimeStamp = Instant.parse(endTime);
        }
        
        setTotalTime();
    } 
    
    public TimesheetEntry(String user, String comment) {
        //this.id = 123;
        this.comment = comment;
        this.complete = false;
        this.username = user;
        this.beginTimeStamp = Instant.now();
        this.endTimeStamp = null;
    } 
    
    public void setComment(String comment) {
        this.comment = comment;
    } 
    
    public String getComment() {
        return this.comment;
    } 
    
    public void setId(int id) {
        this.id = id;
    } 
    
    public int getId() {
        return this.id;
    } 
    
    public boolean getComplete() {
        return this.complete;
    } 
    
    public String getUsername() {
        return this.username;
    } 
    
    public String getBeginTime() {
        return this.beginTimeStamp.toString();
    } 
    
    public String getBeginTimeFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());
        
        return formatter.format(this.beginTimeStamp);
    } 
        
    public String getEndTime() {
        if (this.endTimeStamp == null) {
            return "";
        } else {
            return this.endTimeStamp.toString();
        }
    } 
    
    public String getEndTimeFormatted() {
        if (this.endTimeStamp == null) {
            return "";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());
        
            return formatter.format(this.endTimeStamp);
        }
    }
    
    public void setComplete() {
        this.complete = true;
        this.endTimeStamp = Instant.now();
        setTotalTime();
    } 
    
    public void setTotalTime() {
        if (getComplete()) {
            Duration timeDiff = Duration.between(beginTimeStamp, endTimeStamp);
            this.totalTime = timeDiff.toMinutes();
        }
    }
    
    public String getTotalTime() {
        if (getComplete()) {
            return String.valueOf((int) Math.ceil(this.totalTime / 60.0));
        }
        return "";
    }
    
    @Override
    public boolean equals(Object obj)  {

        if (!(obj instanceof TimesheetEntry))  {
            return false;
        } 

        TimesheetEntry other = (TimesheetEntry) obj;
        return id == other.id;
    } 

    @Override
    public int compareTo(TimesheetEntry e) {
        
        if (e.getId() < this.id) {
            return 1;
        } else if (e.getId() > this.id) {
            return -1;
        } else {
            return 0;
        }
    }
} 
