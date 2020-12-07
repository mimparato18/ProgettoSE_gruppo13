/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.*;

import businesslayer.Planner;
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
public class LoginTest {

    static SystemAdministratorDataAccess administrator;
    static Login instance;
    static Planner userP = new Planner("testPlanner", "hjdgfjagjf");

    public LoginTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        administrator = new SystemAdministratorDataAccess();
        instance = new Login();
        administrator.connect();
        instance.connect();
        boolean init = administrator.createUser(userP, "Planner");
    }

    @AfterClass
    public static void tearDownClass() {
        administrator.removeUser("testPlanner");
        administrator.disconnect();
        instance.disconnect();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUser method, of class Login.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String username = "testPlanner";
        Planner expResult = userP;
        Planner result = (Planner) instance.getUser(username);
        assertEquals(expResult.getUsername(), result.getUsername());
        assertEquals(expResult.getPassword(), result.getPassword());
    }

    /**
     * Test of getRole method, of class Login.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        String username = "testPlanner";
        String expResult = "Planner";
        String result = instance.getRole(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class Login.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String username = "testPlanner";
        String expResult = "hjdgfjagjf";
        String result = instance.getPassword(username);
        assertEquals(expResult, result);
    }

}
