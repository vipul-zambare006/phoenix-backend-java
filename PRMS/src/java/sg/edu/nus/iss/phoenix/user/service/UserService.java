package sg.edu.nus.iss.phoenix.user.service;

import java.sql.SQLException;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.dao.impl.UserDaoImpl;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import java.util.ArrayList;

/**
 *
 * @author Rach 
 */
public class UserService {
    
	DAOFactoryImpl factory;
        UserDao userDao;
        
   public UserService(){
       super();
       factory = new DAOFactoryImpl();
       userDao = factory.getUserDAO();
   }
/** 
 * processCreate. This method is used to process the create user.
 * @param user 
 */
   public void processCreate(User user){
       try{
           userDao.create(user);
       }catch(SQLException e){
           e.printStackTrace();
       }
   }
/**
 * processModify. This method is used to start the process for modify user.
 * @param user 
 */   
    public void processModify(User user) {
         try{
             userDao.save(user);
         }catch (NotFoundException e) {
		// TODO Auto-generated catch block
                e.printStackTrace();
         }catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	 }
     }
/**
 * processDelete. This method is used to start delete process of the users.
 * @param name 
 */         
     public void processDelete(String name)
     {
         try{
             User user = new User(name);
             userDao.delete(user);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
     }
/**
 * findAllUsers. This method is used to find all users and returns the list of users.
 * @return ArrayList of type User
 */     
     public ArrayList<User> findAllUsers(){
         ArrayList<User> userList = new ArrayList<User>();
         try {
             userList= (ArrayList<User>)userDao.loadAll();
             
         } catch(SQLException e){
             e.printStackTrace();
         }
         return userList;
     }
}

