/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet;

import timesheet.domain.*;
import java.util.*;

/**
 *
 * @author Jukka
 */

public class Main {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        //temporary cli
        System.out.println("Enter username: ");
        String uname = sc.nextLine();
        
        User cuser = new User(uname);
        
        TimesheetService cliSession = new TimesheetService(cuser);
        
    }
}