/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service;

import java.sql.SQLException;
import java.util.ArrayList;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author default
 */
public class ScheduleService {

    DAOFactoryImpl factory;
    ScheduleDAO scheduleDao;
    ProgramSlotDAO programSlotDao;

    public ScheduleService() {
        super();
        // Sorry. This implementation is wrong. To be fixed.
        factory = new DAOFactoryImpl();
        scheduleDao = factory.getScheduleDAO();
        programSlotDao = factory.getProgramSlotDAO();
    }

    /*public ArrayList<ProgramSlot> searchPrograms(RadioProgram rpso) {
		ArrayList<RadioProgram> list = new ArrayList<RadioProgram>();
		try {
			list = (ArrayList<RadioProgram>) rpdao.searchMatching(rpso);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<RadioProgram> findRPByCriteria(RadioProgram rp) {
		ArrayList<RadioProgram> currentList = new ArrayList<RadioProgram>();

		try {
			currentList = (ArrayList<RadioProgram>) rpdao.searchMatching(rp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return currentList;

	}

	public RadioProgram findRP(String rpName) {
		RadioProgram currentrp = new RadioProgram();
		currentrp.setName(rpName);
		try {
			currentrp = ((ArrayList<RadioProgram>) rpdao
					.searchMatching(currentrp)).get(0);
			return currentrp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentrp;

	}

	public ArrayList<RadioProgram> findAllRP() {
		ArrayList<RadioProgram> currentList = new ArrayList<RadioProgram>();
		try {
			currentList = (ArrayList<RadioProgram>) rpdao.loadAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentList;

	}*/
    public void processCreate(ProgramSlot programSlot) {
        try {
                if (! isProgramSlotExists(programSlot)) 
                {
                    if (!isScheduleExists(programSlot)) 
                    {
                        scheduleDao.create(programSlot);
                    }
                    programSlotDao.create(programSlot);
                }
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    /*public void processModify(RadioProgram rp) {
		
			try {
				rpdao.save(rp);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}*/

	public void processDelete(ProgramSlot ps) {
        try {
                programSlotDao.delete(ps);
            } catch (NotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	}*/
    private boolean isProgramSlotExists(ProgramSlot programSlot) throws SQLException {
        return programSlotDao.isProgramSlotExists(programSlot);
    }
    
    private boolean isScheduleExists(ProgramSlot programSlot) throws SQLException {
        return scheduleDao.isScheduleExists(programSlot);
    }

}