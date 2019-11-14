/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet;

import timesheet.domain.*;
import java.util.*;
import timesheet.dao.*;

/**
 *
 * @author Jukka
 */

public class Main {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        //temporary cli
        //System.out.println("Enter username: ");
        String uname = "test";//sc.nextLine();
        //System.out.println("Enter name: ");
        String name = "Test";//sc.nextLine();
        
        User cuser = new User(uname, name);
        
        try{
            TimesheetService cliSession = new TimesheetService(new DbTimesheetDao(), new DbUserDao());
        } catch(Exception ex){
            
        }
        
    }
}
