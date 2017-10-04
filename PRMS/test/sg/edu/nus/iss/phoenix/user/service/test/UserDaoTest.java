/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service.test;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;

/**
 *
 * @author Rach
 */
public class UserDaoTest {
    
    private static User user = null;
    
    @Mock
    private static UserDao userDao;
    
    public UserDaoTest() {
    }
    
    @BeforeClass
    public static void setup() throws SQLException{
        userDao = mock(UserDao.class);
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(new Role("Presenter"));
        roles.add(new Role("Producer"));
        user = new User("testID","testName", roles);
        User testUser = new User("Test","Test", roles);
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(user);
        userList.add(testUser);
        //Stubs
        when(userDao.loadAll()).thenReturn(userList);
        
    }
    
    @Test(expected=Exception.class)
    public void testFindAllUsers() throws SQLException{
        List<User> allUsers = userDao.loadAll();
        User myUser = allUsers.get(0);
        assertEquals("testID", myUser.getId());
    }
    
   @Test(expected=Exception.class)
   public void testprocessCreate() throws SQLException{
       Mockito.doThrow(new Exception()).doNothing().when(userDao).create(user);
   }
   
   @Test(expected=Exception.class)
   public void testprocessModify() throws SQLException, NotFoundException{
       Mockito.doThrow(new Exception()).doNothing().when(userDao).save(user);
   }
   
   @Test(expected=Exception.class)
   public void testprocessDelete() throws SQLException, NotFoundException{
       Mockito.doThrow(new Exception()).doNothing().when(userDao).delete(user);
   }
    
}
