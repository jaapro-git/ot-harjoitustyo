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
    static String varUname2;
    static String name;
    static TimesheetService session;
    
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
        varUname = "test"+java.time.Instant.now().toString();
        varUname2 = "createtest"+java.time.Instant.now().toString();
        uname = "tester";
        name = "Testaaja";
        
        try{
            session = new TimesheetService(new DbTimesheetDao(false), new DbUserDao(false));
            session.newUser(uname, name);
            session.newUser(varUname, name);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
            fail("session not created");
        }
    }
    
    @After
    public void tearDown() {
        session.userLogout();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    @Test
    public void userCreateTest(){
        
        try{
            assertEquals(true, session.newUser(varUname2, name));
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error!");
        }
    }
    
    @Test
    public void userLoginTest(){
        
        try{
            assertEquals(true, session.userLogin(uname));
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error!");
        }
    }
    
    @Test
    public void createEntries(){
        
        try{
            if(session.userLogin(varUname)) {
                session.createTimeSheetEntry("Test comment 1");
                session.createTimeSheetEntry("Test comment 2");
                session.createTimeSheetEntry("Test comment 3");                   
            }  
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error!");
        }
        
        assertEquals(session.getEntries().size(), 3);
    }
    
    @Test
    public void listEntries(){
        if(session.userLogin(varUname)) {
            for(TimesheetEntry e:session.getEntries()){
                assertEquals(e.getUsername(), varUname);
            }
        }
    }
    
    @Test
    public void userLogoutTest(){
        
        session.userLogin(uname);
        assertEquals(session.getCurrentUser().getUsername(), uname);
        session.userLogout();
        assertEquals(session.getCurrentUser(), null);
    }
    
    @Test
    public void deleteEntries(){
        int count = 0;
        try{
            if(session.userLogin(varUname)) {
                count = session.getEntries().size();
                int id = session.createTimeSheetEntry("Test entry to be deleted");
                session.deleteTimeSheetEntry(id);
            }  
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error!");
        }
        
        assertEquals(session.getEntries().size(), count);
    }
    
    @Test
    public void completeEntry(){
        int id = 0;
        try{
            if(session.userLogin(varUname)) {
                id = session.createTimeSheetEntry("Test entry to be completed");
                session.setComplete(id);
            }  
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("SQL Error!");
        }
        
        for(TimesheetEntry e:session.getEntries()){
            if(e.getId() == id){
                assertEquals(e.getComplete(), true);
            }
        }
    }
}
