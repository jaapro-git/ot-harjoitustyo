/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timesheet.dao;

import timesheet.domain.User;
import java.util.List;

/**
 *
 * @author Jukka
 */
public interface UserDao {
    
    boolean create(User user) throws Exception;
    
    User findByUname(String uname) throws Exception;
    
    List<User> getUsers() throws Exception;

}
