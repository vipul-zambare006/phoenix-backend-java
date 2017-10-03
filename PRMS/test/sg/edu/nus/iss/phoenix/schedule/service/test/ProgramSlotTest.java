/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author Rach
 */
public class ProgramSlotTest extends TestCase {
    private ProgramSlot ps1 = null;
    private ProgramSlot ps2 = null;
    
    @Before
    public void setUp() throws Exception{
        ps1 = new ProgramSlot("60","2017-09-09","pointyhead","13:00:00","dance floor","dilbert","wally");
        ps2 = new ProgramSlot("40","2017-09-19","pointyhead","09:00:00",null,"dilbert","wally");
    }
    
    @After
    public void tearDown() throws Exception {
	ps1 = null;
	ps2 = null;
    }
    
    @Test
    public void testProgramSlot(){
        
        assertEquals("60",ps1.getDuration());
        assertEquals("2017-09-09",ps1.getDateOfProgram());
        assertEquals("pointyhead",ps1.getassignby());
        assertEquals("13:00:00",ps1.getStartTime());
        assertEquals("dance floor",ps1.getRadioProgramId());
        assertEquals("dilbert",ps1.getPresenterId());
        assertEquals("wally",ps1.getProducerId());
        
        assertEquals("40",ps2.getDuration());
        assertNull(ps2.getRadioProgramId());
        
    }
    
    @Test
    public void testGetDuration(){
        assertEquals("60",ps1.getDuration());
    }
    
    @Test
    public void testGetDateOfProgram(){
        assertEquals("2017-09-09",ps1.getDateOfProgram());
    }
    
    @Test
    public void testGetStartTime(){
        assertEquals("13:00:00",ps1.getStartTime());
        assertFalse(ps1.equals(ps2));
    }
    
    @Test
    public void testGetPresenterId(){
        assertEquals("dilbert",ps1.getPresenterId());
    }
    
    @Test
    public void testGetProducerId(){
        assertEquals("dilbert",ps1.getPresenterId());
    }
    
    @Test
    public void testGetRadioProgramId(){
        assertEquals("dance floor",ps1.getRadioProgramId());
        assertFalse(ps1.equals(null));
    }
    
    @Test
    public void testGetassignby(){
        assertEquals("pointyhead",ps1.getassignby());
        assertSame(ps1,ps1);
    }
    
    @Test
    public void testGetEndTime(){
        assertEquals("14:00",ps1.getEndTime());
    }
}
