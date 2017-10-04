/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.core.exceptions.NotFoundException;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

public class ScheduleServiceTest extends TestCase {

    private static ProgramSlot programSlot = null;
    private static ArrayList<ProgramSlot> programSlotList = new ArrayList<>();

    @Mock
    private ScheduleService service;
    private static ProgramSlotDAO programSlotDao;
    private static DAOFactoryImpl factory;
    private List<ProgramSlot> programList;
    private ProgramSlot programSlot1;

    public ScheduleServiceTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        DAOFactoryImpl daoImpl = mock(DAOFactoryImpl.class);
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

        programList = new ArrayList<ProgramSlot>();
        programList.add(programSlot1);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLoadAllProgramSlots() throws SQLException {
        assertEquals("2016-09-11 00:00:00", programSlot1.getDateOfProgram());
    }

    @Test(expected = NotFoundException.class)
    public void testprocessModify() throws NotFoundException {
        System.out.println("modifySchedule");

        programSlot1.setDateOfProgram("2016-09-11 00:15:00");
        programSlot1.setDuration("00:30:00");
        programSlot1.setRadioProgramId("testModifyProgram");
        programSlot1.setStartTime("2016-09-11 00:15:00");
        doThrow(new NotFoundException("")).when(service).processModify(programSlot1);

    }

    @Test(expected = SQLException.class)
    public void testProcessDelete() throws SQLException, NotFoundException {
        System.out.println("processDelete");
        programSlot1 = new ProgramSlot();

        programSlot1.setDuration("00:30:00");
        programSlot1.setDateOfProgram("2016-09-11 00:00:00");
        programSlot1.setStartTime("15:00:00");
        programSlot1.setPresenterId("dilbert");
        programSlot1.setProducerId("dilbert");
        programSlot1.setRadioProgramId("Latest Hits");
        programSlot1.setassignby("pointyhead");
        doThrow(new SQLException()).when(service).processDelete(programSlot1);

    }
}
