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
   
   public void processCreate(User user){
       try{
           userDao.create(user);
       }catch(SQLException e){
           e.printStackTrace();
       }
   }
   
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

