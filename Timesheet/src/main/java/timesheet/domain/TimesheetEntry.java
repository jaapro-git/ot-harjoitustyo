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
public class TimesheetEntry implements Comparable<TimesheetEntry> {
    
    private int id;
    private String comment;
    private boolean complete;
    private String username;
    private Instant beginTimeStamp;
    private Instant endTimeStamp;
    private long totalTime;
      
    /**
     * Creates a new entry with all required data. Called when retrieving records from the database.
     * @param id
     * @param comment
     * @param complete
     * @param user
     * @param beginTime
     * @param endTime
     */
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
    
    /**
     * Creates a new entry during runtime.
     * @param user
     * @param comment
     */
    public TimesheetEntry(String user, String comment) {
        this.comment = comment;
        this.complete = false;
        this.username = user;
        this.beginTimeStamp = Instant.now();
        this.endTimeStamp = null;
    } 
    
    /**
     *
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    } 
    
    /**
     *
     * @return
     */
    public String getComment() {
        return this.comment;
    } 
    
    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    } 
    
    /**
     *
     * @return
     */
    public int getId() {
        return this.id;
    } 
    
    /**
     *
     * @return
     */
    public boolean getComplete() {
        return this.complete;
    } 
    
    /**
     *
     * @return
     */
    public String getUsername() {
        return this.username;
    } 
    
    /**
     *
     * @return
     */
    public String getBeginTime() {
        return this.beginTimeStamp.toString();
    } 
    
    /**
     * Returns the entry begin time with the local timezone and European formatting.
     * @return
     */
    public String getBeginTimeFormatted() {
        return formatDate(this.beginTimeStamp);
    } 
        
    /**
     *
     * @return
     */
    public String getEndTime() {
        if (this.endTimeStamp == null) {
            return "";
        } else {
            return this.endTimeStamp.toString();
        }
    } 
    
    /**
     * Returns the entry end time with the local timezone and European formatting.
     * @return
     */
    public String getEndTimeFormatted() {
        if (this.endTimeStamp == null) {
            return "";
        } else {
            return formatDate(this.endTimeStamp);
        }
    }
    
    /**
     *
     */
    public void setComplete() {
        this.complete = true;
        this.endTimeStamp = Instant.now();
        setTotalTime();
    } 
    
    /**
     * Calculates the spent time for completed entries in minutes
     */
    public void setTotalTime() {
        if (getComplete()) {
            Duration timeDiff = Duration.between(beginTimeStamp, endTimeStamp);
            this.totalTime = timeDiff.toMinutes();
        }
    }
    
    /**
     * Returns the total time in hours for the UI
     * @return
     */
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
    
    private String formatDate(Instant dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMAN).withZone(ZoneId.systemDefault());
        return formatter.format(dateTime);
    }
} 
