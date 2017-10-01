/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;
import static org.powermock.api.mockito.PowerMockito.whenNew;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import sg.edu.nus.iss.phoenix.core.dao.DAOFactoryImpl;
import sg.edu.nus.iss.phoenix.schedule.dao.ProgramSlotDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ScheduleService;

/**
 *
 * @author Vipul_Zambare
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Static.class)
public class ScheduleServiceTest {

    private static ProgramSlot programSlot = null;
    private static ScheduleService scheduleService;
    private static ArrayList<ProgramSlot> programSlotList = new ArrayList<>();
   
    @Mock
    private static ProgramSlotDAO programSlotDao;
    private static DAOFactoryImpl factory;
    
    public ScheduleServiceTest() {

    }

    @BeforeClass
    public static void setup() throws SQLException, Exception 
    {
        DAOFactoryImpl daoImpl =  mock(DAOFactoryImpl.class);
        whenNew(DAOFactoryImpl.class).withNoArguments().thenReturn(daoImpl);
        programSlotDao = mock(ProgramSlotDAO.class);
        when(daoImpl.getProgramSlotDAO()).thenReturn(programSlotDao);
        
        programSlotList.add(new ProgramSlot("60", "2017-09-09", "pointyhead", "13:00:00", "dance floor", "dilbert", "wally"));
        programSlotList.add(new ProgramSlot("90", "2017-09-12", "pointyhead", "12:00:00", "newz", "kilbert", "wally"));
        programSlotList.add(new ProgramSlot("15", "2017-09-15", "pointyhead", "15:00:00", "pop songs", "wally", "wally"));
        programSlotList.add(new ProgramSlot("30", "2017-09-20", "pointyhead", "08:00:00", "ad-break", "dilbert", "wally"));
    }

    @Test
    public void testLoadAllProgramSlots() throws SQLException {
        when(programSlotDao.loadAll()).thenReturn(programSlotList);
        scheduleService = new ScheduleService();
        
        List<ProgramSlot> programSlots = scheduleService.loadAll();
        ProgramSlot programSlot1 = programSlots.get(0);
        assertEquals("2017-09-09", programSlot1.getDateOfProgram());
    }
}
