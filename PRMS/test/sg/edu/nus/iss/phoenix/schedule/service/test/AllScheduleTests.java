/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.service.test;

import junit.framework.TestSuite;
/**
 *
 * @author Rach
 */

public class AllScheduleTests {
    public static TestSuite suite() throws Exception {
        TestSuite suite=new TestSuite("Store Tests");
        suite.addTestSuite(ProgramSlotDaoTest.class);
        suite.addTestSuite(ProgramSlotTest.class);
        suite.addTestSuite(ScheduleServiceTest.class);
        return suite;
    }
}

