/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Calabrese
 */
public class PlannerServiceTest {
    
    public PlannerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addActivity method, of class PlannerService.
     */
    @Test
    public void testAddActivity() {
        System.out.println("addActivity");
        PlannerService instance = null;
        boolean expResult = false;
        boolean result = instance.addActivity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateActivity method, of class PlannerService.
     */
    @Test
    public void testUpdateActivity() {
        System.out.println("updateActivity");
        PlannerService instance = null;
        boolean expResult = false;
        boolean result = instance.updateActivity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteActivity method, of class PlannerService.
     */
    @Test
    public void testDeleteActivity() {
        System.out.println("deleteActivity");
        PlannerService instance = null;
        boolean expResult = false;
        boolean result = instance.deleteActivity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of viewActivities method, of class PlannerService.
     */
    @Test
    public void testViewActivities() {
        System.out.println("viewActivities");
        PlannerService instance = null;
        ArrayList<MaintenanceActivity> expResult = null;
        ArrayList<MaintenanceActivity> result = instance.viewActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
