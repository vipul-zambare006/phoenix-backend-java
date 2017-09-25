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
    
    public void processCreate(ProgramSlot programSlot) {
        try {
            if (!isProgramSlotExists(programSlot)) {
                if (!isScheduleExists(programSlot)) {
                    scheduleDao.create(programSlot);
                }
                programSlotDao.create(programSlot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProgramSlot> findAll() {
        ArrayList<ProgramSlot> currentList = new ArrayList<ProgramSlot>();
        try {
            currentList = (ArrayList<ProgramSlot>) programSlotDao.loadAll();
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return currentList;

    }

    public void processModify(ProgramSlot programSlot) {
        try {
            programSlotDao.save(programSlot);
        } catch (NotFoundException | SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void copySchedule(ProgramSlot programSlot) {
            /* Copy schedule code will come here */
    }

    public void processDelete(ProgramSlot ps) {
        try {
            programSlotDao.delete(ps);
        } catch (NotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isProgramSlotExists(ProgramSlot programSlot) {
        try {
            return programSlotDao.isProgramSlotExists(programSlot);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private boolean isScheduleExists(ProgramSlot programSlot) {
        try {
            return scheduleDao.isScheduleExists(programSlot);
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
