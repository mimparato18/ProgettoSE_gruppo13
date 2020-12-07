/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.MaintenanceActivity;
import businesslayer.Site;
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
public class PlannerDataAccessTest {

    PlannerDataAccess instance = new PlannerDataAccess();
    MaintenanceActivity activityTest = new MaintenanceActivity(new Site("branchOffice1", "departement1"), "typology1", "bla bla bla", 15, true, "materials1, materials2, materials3", 3, "bla bla bla");

    public PlannerDataAccessTest() {
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
     * Test of createActivity method, of class PlannerDataAccess.
     */
    @Test
    public void testCreateActivity() {
        System.out.println("createActivity");
        boolean result = instance.createActivity(activityTest);
        assertTrue(result);
    }

    /**
     * Test of modifyActivity method, of class PlannerDataAccess.
     */
    @Test
    public void testModifyActivity() {
        System.out.println("modifyActivity");
        int id = 0;
        MaintenanceActivity activity = null;

        boolean expResult = false;
        boolean result = instance.modifyActivity(id, activity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeActivity method, of class PlannerDataAccess.
     */
    @Test
    public void testRemoveActivity() {
        System.out.println("removeActivity");
        int id = 0;

        boolean expResult = false;
        boolean result = instance.removeActivity(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllActivities method, of class PlannerDataAccess.
     */
    @Test
    public void testGetAllActivities() {
        System.out.println("getAllActivities");

        ArrayList<MaintenanceActivity> expResult = null;
        ArrayList<MaintenanceActivity> result = instance.getAllActivities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
