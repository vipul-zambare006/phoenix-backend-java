/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;


public class ProgramSlotDaoTest {
    
    private static ProgramSlot programSlot;
    private static ArrayList<ProgramSlot> programSlotList = new ArrayList<>();
    
    @Mock
    private static ProgramSlotDAO dao;
    
    public ProgramSlotDaoTest(){
    }
    
    @BeforeClass
    public static void setup() throws SQLException{
    DAOFactoryImpl daoImpl =  mock(DAOFactoryImpl.class);
        dao = mock(ProgramSlotDAO.class);
        when(daoImpl.getProgramSlotDAO()).thenReturn(dao);
        programSlotList.add(new ProgramSlot("60", "2017-09-09", "pointyhead", "13:00:00", "dance floor", "dilbert", "wally"));
        programSlotList.add(new ProgramSlot("90", "2017-09-12", "pointyhead", "12:00:00", "newz", "kilbert", "wally"));
        programSlotList.add(new ProgramSlot("15", "2017-09-15", "pointyhead", "15:00:00", "pop songs", "wally", "wally"));
        programSlotList.add(new ProgramSlot("30", "2017-09-20", "pointyhead", "08:00:00", "ad-break", "dilbert", "wally"));
    }
    
    @Test(expected=Exception.class)
    public void testLoadAll() throws SQLException{
    
    }
    
    @Test(expected=Exception.class)
    public void testCreate() throws SQLException{
        Mockito.doThrow(new Exception()).doNothing().when(dao).create(programSlot);
        
    }
    
    @Test(expected=Exception.class)
    public void testSave() throws SQLException, NotFoundException{
        Mockito.doThrow(new Exception()).doNothing().when(dao).save(programSlot);
    }
    
     @Test(expected=Exception.class)
    public void testDelete() throws SQLException, NotFoundException{
        Mockito.doThrow(new Exception()).doNothing().when(dao).delete(programSlot);
    }
    
}
