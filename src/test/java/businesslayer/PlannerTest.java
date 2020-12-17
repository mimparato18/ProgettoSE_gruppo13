/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

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
public class PlannerTest {
    
    public PlannerTest() {
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

    @Test
    public void testGetUsername() {
        System.out.println("Get Username - Planner");
        Planner instance = new Planner("testPlanner", "testPlanner");
        String expResult = "testPlanner";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPassword() {
        System.out.println("Get Password - Planner");
        Planner instance = new Planner("testPlanner", "testPlanner");
        String expResult = "testPlanner";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsername() {
        System.out.println("Set Username - Planner");
        Planner instance = new Planner("testPlanner", "testPlanner");
        String expResult = "testPlannerSet";
        instance.setUsername("testPlannerSet");
        assertEquals(expResult, instance.getUsername());
    }

    @Test
    public void testSetPassword() {
        System.out.println("Set Password - Planner");
        Planner instance = new Planner("testPlanner", "testPlanner");
        String expResult = "testPlannerSet";
        instance.setUsername("testPlannerSet");
        assertEquals(expResult, instance.getUsername());
    }
    
}
