package sg.edu.nus.iss.phoenix.user.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author Rach
 */
public class UserDelegate {

    private AuthenticateService service;
    private UserService userService;

    public UserDelegate() {
        service = new AuthenticateService();
        userService = new UserService();
    }
/**
 * manageUser. This method is used to manage the user profile
 * @return list of Users
 */
    public List<User> manageUser() {
       // return service.manageUser();
       return null;
    }
/**
 * createUser. This method is used to create user profiles.
 * @param user 
 */
    public void createUser(User user) {
        userService.processCreate(user);
    }
/** modifyUser. This method is used to modify user.
 * 
 * @param user 
 */
    public void modifyUser(User user) {
        userService.processModify(user);
    }
/**
 * processDelete. This method is used to process Delete user
 * @param name 
 */
    public void processDelete(String name) {
        UserService service = new UserService();
        service.processDelete(name);
    }
}
