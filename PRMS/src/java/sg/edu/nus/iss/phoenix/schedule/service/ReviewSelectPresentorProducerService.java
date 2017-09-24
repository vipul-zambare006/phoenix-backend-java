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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.authenticate.dao.RoleDao;
import sg.edu.nus.iss.phoenix.authenticate.entity.Role;


public class ReviewSelectPresentorProducerService {
    
    DAOFactoryImpl factory;
    RoleDao roledao;
    
    public ReviewSelectPresentorProducerService() {
super();
// TODO Auto-generated constructor stub
factory = new DAOFactoryImpl();
roledao = factory.getRoleDAO();
}
    
    public List<Role> reviewSelectPresentorProducer() {
            List<Role> data = null;
            try {
                data = roledao.loadAll();
            } catch (SQLException ex) {
                Logger.getLogger(ReviewSelectPresentorProducerService.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data; 
}
}
