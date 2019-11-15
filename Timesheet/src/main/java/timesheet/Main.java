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
        System.out.print("Enter username: ");
        String uname = sc.nextLine();
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        
        try{
            TimesheetService cliSession = new TimesheetService(new DbTimesheetDao(), new DbUserDao());
            if(cliSession.userLogin(uname)){
                System.out.println("User "+uname+" successully logged in!");
            }else if(cliSession.newUser(uname, name)){
                System.out.println("New user "+uname+" successully created!");
            }else{
                System.out.println("Something did not work!");
            }    
        } catch(Exception ex){
            
        }
        
    }
}
