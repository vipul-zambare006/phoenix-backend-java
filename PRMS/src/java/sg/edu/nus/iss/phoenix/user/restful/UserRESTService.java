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
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectPresentorProducerService;
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
    private ReviewSelectPresentorProducerService reviewSelectService;

    public UserRESTService() {
        userService = new UserService();
        reviewSelectService = new ReviewSelectPresentorProducerService();
    }
/**
 * getUSer. This method is used to get the user.
 * @return of type User
 */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        throw new UnsupportedOperationException();
    }
/**
 * createUser. This method is used to create a user.
 * @param user 
 */
    @PUT
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createUser(User user) {
        userService.processCreate(user);
    }
/**
 * updateUser. This method is used to update user.
 * @param user 
 */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {
        userService.processModify(user);
    }
/**
 * deleteUser. This method is used to delete user.
 * @param name 
 */
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

 /**
    * getAllUsers. This method GET to retrieve the instance of resource
    * @return type UserList
    */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getAllUsers() {

        ArrayList<User> userList = (ArrayList<User>) userService.findAllUsers();
        UserList userArrayList = new UserList();
        userArrayList.setUserList(new ArrayList<User>());

        for (int i = 0; i < userList.size(); i++) {
            userArrayList.getUserList().add(
                    new User(
                            userList.get(i).getId(),
                            userList.get(i).getName(),
                            userList.get(i).getPassword(),
                            userList.get(i).getRoles()
                    ));
        }
        return userArrayList;
    }
/** 
 * getAllPresenterProducer. This method gets the All list of presenter producer.
 * @return type Array of userList
 */    
    @GET
    @Path("/presenterproducer")
    @Produces(MediaType.APPLICATION_JSON)
    public UserList getAllPresenterProducer() {

        ArrayList<User> userList = (ArrayList<User>) reviewSelectService.reviewSelectPresentorProducer();
        UserList userArrayList = new UserList();
        userArrayList.setUserList(new ArrayList<User>());

        for (int i = 0; i < userList.size(); i++) {
            userArrayList.getUserList().add(
                    new User(
                            userList.get(i).getId(),
                            userList.get(i).getName(),
                            userList.get(i).getPassword(),
                            userList.get(i).getRoles()
                    ));
        }
        return userArrayList;
    }
}
