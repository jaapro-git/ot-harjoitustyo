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
public class User {
    
    String uname;
    String name;
    
    public User(String uname, String name){
        this.uname = uname;
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getUsername(){
        return this.uname;
    }
}
