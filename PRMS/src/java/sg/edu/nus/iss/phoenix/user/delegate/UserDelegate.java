package sg.edu.nus.iss.phoenix.user.delegate;
import java.util.List;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.authenticate.service.AuthenticateService;
import sg.edu.nus.iss.phoenix.user.service.UserService;

/**
 *
 * @author Rach
 */

public class UserDelegate  {
private AuthenticateService service;
private UserService userService;
public UserDelegate()
{
    service = new AuthenticateService();
    userService = new UserService();
}
public List<User> manageUser()
{
    return service.manageUser();
}

public void createUser(User user){
    userService.processCreate(user);
}

public void modifyUser(User user){
    userService.processModify(user);        
}
 
public void processDelete(String name)
    {
        UserService service = new UserService();
	service.processDelete(name);
    }
}