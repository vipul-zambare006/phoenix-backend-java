/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

/**
 *
 * @author monalisadebnth
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.UserDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.User;

public class ReviewSelectPresentorProducerService {

    DAOFactoryImpl factory;
    UserDao userdao;

    public ReviewSelectPresentorProducerService() {
        super();
        factory = new DAOFactoryImpl();
        userdao = factory.getUserDAO();
    }

    public List<User> reviewSelectPresentor() {
        List<User> data = null;
        try {
            data = userdao.loadAllPresenters();
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectPresentorProducerService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public List<User> reviewSelectProducer() {
        List<User> data = null;
        try {
            data = userdao.loadAllProducers();
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(ReviewSelectPresentorProducerService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
