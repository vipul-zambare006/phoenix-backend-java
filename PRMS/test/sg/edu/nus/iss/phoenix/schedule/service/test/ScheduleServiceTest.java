/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import org.mockito.runners.MockitoJUnitRunner;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.service.UserService;

@RunWith(MockitoJUnitRunner.class)

/**
 *
 * @author Vipul_Zambare
 */

public class ScheduleServiceTest {
     
    private static User user = null;
    @Mock
    UserService mUserService;
    public ScheduleServiceTest() {
    }
    
    @BeforeClass
    public static void init() {
	user = new User();
	user.setAll("rachel", "rachel", "createTest- User Name", "admin");
    }
    
    @Test(expected=Exception.class)
    public void testProcessCreate() {
        doThrow(new Exception()).when(mUserService).processCreate(any(User.class));
	mUserService.processCreate(user);
    }

    @Test(expected=Exception.class)
    public void testProcessModify() {
        User modifiedUser = user;
	modifiedUser.setName(" modifyTest- User Name");
	doThrow(new Exception()).when(mUserService).processModify(any(User.class));
	mUserService.processModify(modifiedUser);
    }
    
    @Test(expected=Exception.class)
    public void testProcessDelete() {
	doThrow(new Exception()).when(mUserService).processDelete(any(String.class));
	mUserService.processDelete("rachel");
    }
}