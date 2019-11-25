/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timesheet.dao.DbTimesheetDao;
import timesheet.dao.DbUserDao;
import timesheet.domain.TimesheetEntry;
import timesheet.domain.TimesheetService;

/**
 *
 * @author Jukka
 */
public class TimesheetTest {
    
    static String uname;
    static String varUname;
    static String name;
    static TimesheetService session;
    
    public TimesheetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        varUname = "test"+java.time.Instant.now().toString();
        uname = "test";
        name = "Testaaja";
        
        try{
            session = new TimesheetService(new DbTimesheetDao(false), new DbUserDao(false));
            session.newUser(uname, name);
        } catch (Exception ex){
            
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        session.newUser(uname, name);
        session.userLogin(uname);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void userCreateTest(){
       
        try{
            if(session.newUser(varUname, name)) return;
            fail("Something did not work!"); 
        } catch(Exception ex){
            System.out.println("SQL Error!");
        }
    }
    
    @Test
    public void userLoginTest(){
        
        session.userLogout();
        try{
            if(session.userLogin(uname)) return;
            fail("Something did not work!"); 
        } catch(Exception ex){
            System.out.println("SQL Error!");
        }
    }
    
    @Test
    public void createEntries(){
        
        session.createTimeSheetEntry("Test comment 1");
        session.createTimeSheetEntry("Test comment 2");
        session.createTimeSheetEntry("Test comment 3");
        
        if(session.getEntries().size() == 3) return;
        fail("Something did not work!"); 
    }
    
    @Test
    public void listEntries(){
        for(TimesheetEntry e:session.getEntries()){
            if(e.getUsername() != uname) fail("Something did not work!");
        }
    }
}
