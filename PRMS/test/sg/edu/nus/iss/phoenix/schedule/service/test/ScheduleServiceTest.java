/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import  sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;


public class ScheduleServiceTest {
    
    private static ProgramSlot programSlot = null;
    private static ArrayList<ProgramSlot> programSlotList = new ArrayList<>();
    
    @Mock
     private ScheduleService service;
     private static ProgramSlotDAO programSlotDao;
     private static DAOFactoryImpl factory;
     private List<ProgramSlot> programList ;
     private ProgramSlot programSlot1;
     
     public ScheduleServiceTest() {

    }
    
    @BeforeClass
    public static void setUpClass() {
        DAOFactoryImpl daoImpl =  mock(DAOFactoryImpl.class);
//        whenNew(DAOFactoryImpl.class).withNoArguments().thenReturn(daoImpl);
//        programSlotDao = mock(ProgramSlotDAO.class);
//        when(daoImpl.getProgramSlotDAO()).thenReturn(programSlotDao);
//        
//        programSlotList.add(new ProgramSlot("60", "2017-09-09", "pointyhead", "13:00:00", "dance floor", "dilbert", "wally"));
//        programSlotList.add(new ProgramSlot("90", "2017-09-12", "pointyhead", "12:00:00", "newz", "kilbert", "wally"));
//        programSlotList.add(new ProgramSlot("15", "2017-09-15", "pointyhead", "15:00:00", "pop songs", "wally", "wally"));
//        programSlotList.add(new ProgramSlot("30", "2017-09-20", "pointyhead", "08:00:00", "ad-break", "dilbert", "wally"));
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException, NotFoundException {
       service = Mockito.mock(ScheduleService.class);
       programSlot1 = new ProgramSlot();
    
     programSlot1.setDuration("00:30:00");  
     programSlot1.setDateOfProgram("2016-09-11 00:00:00");
     programSlot1.setStartTime("15:00:00");
     programSlot1.setPresenterId("dilbert");
     programSlot1.setProducerId("dilbert");
     programSlot1.setRadioProgramId("Latest Hits");
     programSlot1.setassignby("pointyhead");
       
     programList=new ArrayList<ProgramSlot>();
     programList.add(programSlot1);
    
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testLoadAllProgramSlots() throws SQLException {
        //when(programSlotDao.loadAll()).thenReturn(programSlotList);
        //scheduleService = new ScheduleService();
        
        //List<ProgramSlot> programSlots = scheduleService.loadAll();
        //ProgramSlot programSlot1 = programSlots.get(0);
        assertEquals("2017-09-09", programSlot1.getDateOfProgram());
    }
    
    @Test(expected=Exception.class)
    public void testProcessCreate() {
        doThrow(new Exception()).when(service).processCreate(any(ProgramSlot.class));
	service.processCreate(programSlot1);
    }
    
    @Test(expected=Exception.class)
    public void testprocessModify() {
        System.out.println("modifySchedule");
        
        programSlot1.setDateOfProgram("2016-09-11 00:15:00");
        programSlot1.setDuration("00:30:00");
        programSlot1.setRadioProgramId("testModifyProgram");
        programSlot1.setStartTime("2016-09-11 00:15:00");
        doThrow(new SQLException()).when(service).processModify(programSlot1);

    }
    
    @Test
    public void testProcessDelete() throws SQLException {
        System.out.println("processDelete");
        doThrow(new SQLException()).when(service).processDelete(programSlot1);
        
    }

    @Test
    public void testcopySchedule() {
   
        //ProgramSlot result = service.copySchedule(programSlot1, " ");
       // assertEquals(programSlot1.getDateOfProgram(), result.getDateOfProgram());
    }
    
    @Test
    public void testisProgramSlotExists() {
        String dateOfProgram = "2016-09-11 00:15:00";
        boolean expResult = true;
        //boolean result = service.isProgramSlotExists();
    }
    
    @Test
    public void testisScheduleExists() {
        
        String startTime = "2016-09-11 00:00:00";
        String duration = "00:30:00";
        when(service.isScheduleExists(programSlot1)).thenReturn(true);
        //String result = service.isScheduleExists();
        //assertEquals("true", result);
    }
    
        

    
}
