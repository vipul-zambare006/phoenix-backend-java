/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Vipul Zambare.
 */
public class ScheduleService {

    DAOFactoryImpl factory;
    ScheduleDAO scheduleDao;
    ProgramSlotDAO programSlotDao;

    public ScheduleService() {
        super();
        factory = new DAOFactoryImpl();
        scheduleDao = factory.getScheduleDAO();
        programSlotDao = factory.getProgramSlotDAO();
    }
/**
 * processCreate.This method is used to create a program slot, hence it checks if the 
 * Program slot exists, if the program slot exists it will throw an error to the user.
 * @param programSlot. this is an instance of ProgramSlot to create a new program slot.
 * @throws SQLException
 */
    public void processCreate(ProgramSlot programSlot) throws SQLException {
        try {
            if (!isProgramSlotExists(programSlot)) {
                if (!isScheduleExists(programSlot)) {
                    scheduleDao.create(programSlot);
                }
                programSlotDao.create(programSlot);
            }
        } catch (SQLException e) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
/**
 * loadAll. This method is used to get all ProgramSlot in an ArrayList
 * @return ArrayList
 * @throws SQLException
 */
    public ArrayList<ProgramSlot> loadAll() throws SQLException {
        ArrayList<ProgramSlot> currentList = new ArrayList();
        try
        {
            currentList = (ArrayList<ProgramSlot>) programSlotDao.loadAll();
        } catch (SQLException e) 
        {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, e);
        }
        return currentList;

    }
/**
 * processModify. This method is used for modify the program slot details which 
 * takes in Program slot instances
 * @param programSlot 
 * @throws NotFoundException SQLException
 */
    public void processModify(ProgramSlot programSlot)throws NotFoundException {
        try 
        {
                programSlotDao.save(programSlot);
        }
        catch (NotFoundException | SQLException ex) 
        {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 /**
  * copySchedule-Method. This method provides the copy schedule from database.
  * It works by calling processCreate method in the same schedule Service class 
  * and program slot objects is passed in the method objects
  *
  * 
  * @param programSlot
  * This parameter contains the class instance where program slot details is available.	 
  * @throws java.sql.SQLException	 
  */
    
    public void copySchedule(ProgramSlot programSlot) throws SQLException {
        try {
        processCreate(programSlot);
        } catch (SQLException e) {
        Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, e);
        }
    }
/**
 * processDelete. This method does the delete functionality from the program slot, it takes 
 * programSlot instances and deletes the program from the program slot
 * @param ps
 * @throws sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException
     * @throws java.sql.SQLException
 */
    public void processDelete(ProgramSlot ps) throws NotFoundException, SQLException {
                    programSlotDao.delete(ps);
        
    }
/**
 * isProgramSlotExists. This method is used to check if ProgramSlot exists in the program slot.
 * @param programSlot
 * @return boolean
 * @throws SQLException
 */
    private boolean isProgramSlotExists(ProgramSlot programSlot) {
        try {
            return programSlotDao.isProgramSlotExists(programSlot);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
/**
 * isScheduleExists. Is a method to check if the schedule exist in the schedule
 * @param programSlot
 * @return boolean
 */
    public boolean isScheduleExists(ProgramSlot programSlot) {
        try {
            return scheduleDao.isScheduleExists(programSlot);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
