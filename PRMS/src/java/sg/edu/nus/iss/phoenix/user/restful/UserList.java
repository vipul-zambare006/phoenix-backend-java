/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

/**
 *
 * @author Rach
 */
public class UserList {
    private List<User> userList;
/**
 * getSUerList. This method is used to get the list of users.
 * @return 
 */    
    public List<User> getUserList(){
        return userList;
    }
/**
 * setUserList. This method set the user list.
 * @param userList 
 */       
    public void setUserList(List<User> userList){
        this.userList = userList;
    }
}
