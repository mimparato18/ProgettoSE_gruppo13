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
public class LoginServiceTest {

    LoginService instance = new LoginService();
    static SystemAdministratorService admin = new SystemAdministratorService(new SystemAdministrator("admin","admin"));
    static Planner testPlanner = new Planner("testCredentials", "testCredentials");

    public LoginServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        boolean init = admin.addUser(testPlanner.getUsername(), testPlanner.getUsername(), "Planner");
    }

    @AfterClass
    public static void tearDownClass() {
        admin.deleteUser("testCredentials");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkCredentials method, of class LoginService.
     */
    @Test
    public void testCheckCredentials() {
        System.out.println("checkCredentials");
        String expResult = "Planner";
        String result = instance.checkCredentials(testPlanner.getUsername(), testPlanner.getPassword());
        assertEquals(expResult, result);
    }

}
