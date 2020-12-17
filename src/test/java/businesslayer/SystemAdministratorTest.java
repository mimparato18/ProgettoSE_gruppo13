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
public class SystemAdministratorTest {

    SystemAdministrator instance = new SystemAdministrator("admin", "admin");

    public SystemAdministratorTest() {
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
     * Test of createPlanner method, of class SystemAdministrator.
     */
    @Test
    public void testCreatePlanner() {
        System.out.println("createPlanner");
        String username = "testPlanner";
        String password = "testPlanner";
        Planner result = instance.createPlanner(username, password);
        assertTrue(result instanceof Planner);
    }

    /**
     * Test of createMaintainer method, of class SystemAdministrator.
     */
    @Test
    public void testCreateMaintainer_String_String() {
        System.out.println("createMaintainer");
        String username = "testMaintainer";
        String password = "testMaintainer";
        Maintainer result = instance.createMaintainer(username, password);
        assertTrue(result instanceof Maintainer);
    }

    /**
     * Test of createMaintainer method, of class SystemAdministrator.
     */
    @Test
    public void testCreateMaintainer_3args() {
        System.out.println("createMaintainer with competencies");
        String username = "testMaintainer_3arg";
        String password = "testMaintainer_3arg";
        ArrayList<String> competencies = new ArrayList<String>();
        for(int i=0;i<6;i++){
            competencies.add("competencies"+i);
        }
        Maintainer result = instance.createMaintainer(username, password, competencies);
        assertTrue(result instanceof Maintainer);
    }

}
