/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service.test;
import java.sql.SQLException;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;

import sg.edu.nus.iss.phoenix.user.service.*;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Rach
 */
public class UserServiceTest {
    
    private static User user = null;
    
    @Mock
    private static UserService userService;
    
    public UserServiceTest() {
    }
    
    @BeforeClass
    public static void setup() throws SQLException{
        user = new User();
	user.setAll("testID", "testPassword", "testName", "testRole");
        
    }
    
    @Test(expected=Exception.class)
    public void testProcessCreate() {
        doThrow(new Exception()).when(userService).processCreate(any(User.class));
	userService.processCreate(user);
    }
    
    @Test(expected=Exception.class)
    public void testProcessModify() {
        User modifiedUser = user;
	modifiedUser.setName("testModifyUser");
	doThrow(new Exception()).when(userService).processModify(any(User.class));
	userService.processModify(modifiedUser);
    }
    
    @Test(expected=Exception.class)
    public void testProcessDelete() {
	doThrow(new Exception()).when(userService).processDelete(any(String.class));
	userService.processDelete("testModifyUser");
    }
    
}
