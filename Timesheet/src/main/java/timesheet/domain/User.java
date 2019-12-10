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
    
    private String uname;
    private String name;
    private String type;
    
    /**
     * Constructor for a new user with given username and name
     * @param uname
     * @param name
     */
    public User(String uname, String name) {
        this.uname = uname;
        this.name = name;
    }
    
    /**
     * Dummy user
     */
    public User() {
        this.uname = null;
        this.name = null;
    }
    
    /**
     *
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     *
     * @return
     */
    public String getUsername() {
        return this.uname;
    }
}
