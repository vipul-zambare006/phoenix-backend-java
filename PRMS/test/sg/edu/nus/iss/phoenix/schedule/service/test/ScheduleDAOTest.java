/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.BeforeClass;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import sg.edu.nus.iss.phoenix.schedule.dao.ScheduleDAO;
import sg.edu.nus.iss.phoenix.schedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;


public class ScheduleDAOTest {
    
    private static ProgramSlot ps= null;
    private static AnnualSchedule annualSchedule = null;
    
    @Mock
    private static ScheduleDAO scheduleDao;
    
    public ScheduleDAOTest(){}
    
    @BeforeClass
    public static void setup() throws SQLException{
    scheduleDao = mock(ScheduleDAO.class);
    
    ArrayList<AnnualSchedule> aList = new ArrayList<AnnualSchedule>();
    
    }
}
