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
import sg.edu.nus.iss.phoenix.user.restful.UserList;
import sg.edu.nus.iss.phoenix.user.service.UserService;


/**
 *
 * @author Rach
 */

@Path("user")
@RequestScoped
public class UserRESTService {
    
     @Context
    private UriInfo context;
    
    private UserService userService;

    public UserRESTService() {
        userService = new UserService();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
//    @GET
//    @Path("/all")
//    @Produces(MediaType.APPLICATION_JSON)
//    public User getAllUsers() {
//        ArrayList<User> userList = userService.findAllUsers();
//        User usersList = new User();
//        usersList.setUserList(new ArrayList<User>());
//        
//        for (int i = 0; i < userList.size(); i++) {
//            usersList.getUserList().add(
//                new User(userList.get(i).getId(),
//                         userList.get(i).getName())
//                        );
//        }
//
//        return usersList;
//    }
    
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user){
        userService.processCreate(user);
    }
    
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user){
        userService.processModify(user);
    }
    
    @DELETE
    @Path("/delete/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteUser(@PathParam("userName") String name) {
        String name2;
        try { 
            name2 = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        userService.processDelete(name2);
    }
    
}
