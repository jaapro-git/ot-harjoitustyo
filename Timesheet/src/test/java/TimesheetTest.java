/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timesheet.dao.DbTimesheetDao;
import timesheet.dao.DbUserDao;
import timesheet.domain.TimesheetService;

/**
 *
 * @author Jukka
 */
public class TimesheetTest {
    
    public TimesheetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

//    @Test
//    public void userCreateTest(){
//        
//        String uname = "test"+java.time.Instant.now().toString();
//        String name = "Testaaja";
//        
//        try{
//            TimesheetService cliSession = new TimesheetService(new DbTimesheetDao(), new DbUserDao());
//            if(cliSession.newUser(uname, name)) return;
//            fail("Something did not work!"); 
//        } catch(Exception ex){
//            System.out.println("SQL Error!");
//        }
//    }
    
    @Test
    public void userLoginTest(){
        
        String uname = "test";
        String name = "Testaaja";
        
        try{
            TimesheetService cliSession = new TimesheetService(new DbTimesheetDao(), new DbUserDao());
            cliSession.newUser(uname, name);
            
            if(cliSession.userLogin(uname)) return;
            fail("Something did not work!"); 
        } catch(Exception ex){
            System.out.println("SQL Error!");
        }
    }
}
