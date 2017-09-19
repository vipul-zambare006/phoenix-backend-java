/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.user.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.user.restful.Users;
import sg.edu.nus.iss.phoenix.user.service.UserService;


/**
 *
 * @author Rach
 */

@Path("testradioprogram")
@RequestScoped
public class UserRESTService {
    
     @Context
    private UriInfo context;
    
    private UserService userService;

    /**
     * Creates a new instance of RadioProgramRESTService
     */
    public UserRESTService() {
        userService = new UserService();
    }
    
    /**
     * Retrieves representation of an instance of resource
     * @return an instance of resource
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Users getAllUsers() {
        ArrayList<User> userList = userService.findAllUsers();
        Users usersList = new Users();
        usersList.setUserList(new ArrayList<User>());
        
        for (int i = 0; i < userList.size(); i++) {
            usersList.getUserList().add(
                new User(userList.get(i).getName()));
        }

        return usersList;
    }
    
}
