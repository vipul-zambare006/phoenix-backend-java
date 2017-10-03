/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.service.test;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Rach
 */
public class UserTest extends TestCase{
    
    private static User user = null;
    @Before
    public void setup() throws Exception{
    }

    @After
    public void tearDown() throws Exception{	
    }
    
    @Test
    public void testUserConstructor() throws Exception{
        
        String userID = "pointyhead";
        User user = new User(userID);
        assertEquals(user.getId(),userID);
    }
    
    @Test
    public void testSetGetName() throws Exception{
        
        String userName = "pointyhead, the manager";
        User user = new User(userName);
        user.setName(userName);
        String userNameTest = user.getName();
        assertEquals(userName,userNameTest);
    }
}
