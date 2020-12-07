/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Maintainer;
import businesslayer.Planner;
import businesslayer.Procedure;
import businesslayer.Site;
import businesslayer.User;
import java.sql.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Ignore;

/**
 *
 * @author Marco Calabrese
 */
public class SystemAdministratorDataAccessTest {

    static SystemAdministratorDataAccess instance;

    static Login instanceLogin = new Login();

    static Planner userP = new Planner("testPlanner", "hjdgfjagjf");
    Planner userCreate = new Planner("testPlannerCreate", "hjdgfjagjf");
    static Planner userModify = new Planner("testPlannerMod", "hjdgfjagjf");
    static ArrayList<String> competence = new ArrayList<String>();
    static Maintainer userM = new Maintainer("testMaintainer", "hjdgfjagjf", competence);

    static Site site = new Site("testFactorySite0", "testdepartment");
    static Site siteCreate = new Site("testFactorySite1", "testdepartment");
    static Site siteMod = new Site("testFactorySite2", "testdepartment");

    String type = "testTypology0";
    static String isType = "isType";
    static String typeModify = "testTypology1";
    static String otherTypology = "testTypology2";

    public SystemAdministratorDataAccessTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        instance = new SystemAdministratorDataAccess();
        instance.connect();
        instanceLogin.connect();
        competence.add("competence1");
        competence.add("competence2");
        competence.add("competence3");
        boolean init = instance.createUser(userM, "Maintainer");
        init = instance.createUser(userModify, "Planner");
        init = instance.createUser(userP, "Planner");
        init = instance.createSite(site);
        init = instance.createSite(siteMod);
        init = instance.createTypology(isType);
        init = instance.createTypology(typeModify);
        init = instance.removeTypology(otherTypology);
    }

    @AfterClass
    public static void tearDownClass() {
        instance.removeUser("testMaintainer");
        instance.removeUser("testPlannerMod");
        instance.removeUser("testPlannerCreate");
        instance.removeSite(siteCreate);
        instance.removeSite(siteMod);
        instance.removeTypology(isType);
        instance.removeTypology(typeModify);
        instance.removeTypology(otherTypology);
        instance.disconnect();
        instanceLogin.disconnect();
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of Connection method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    //@Test(expected = SQLException.class)
    @Test
    public void testConnection() {
        System.out.println("Connection");
        boolean result = instance.connect();
        assertTrue(result);
    }

    /**
     * Test of isUser method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testIsUser() {
        System.out.println("isUser");
        String username = userCreate.getUsername();
        boolean result = instance.isUser(username);
        assertTrue(result);
    }

    /**
     * Test of getUser method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        String username = "testMaintainer";
        Maintainer expResult = userM;
        Maintainer result = (Maintainer) instanceLogin.getUser(username);
        assertEquals(userM.getUsername(), result.getUsername());
        assertEquals(userM.getPassword(), result.getPassword());
    }

    /**
     * Test of getRole method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        String username = "testPlanner";
        String expResult = "Planner";
        String result = instanceLogin.getRole(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPassword method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String username = "testPlanner";
        String expResult = "hjdgfjagjf";
        String result = instanceLogin.getPassword(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of createUser method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    //@Test(expected = SQLIntegrityConstraintViolationException.class)
    @Test
    public void testCreateUser() /*throws SQLException*/ {
        System.out.println("createUser");
        String role = "Planner";
        boolean result = instance.createUser(userCreate, role);
        assertTrue(result);

    }

    /**
     * Test of modifyUser method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testModifyUser() {
        System.out.println("modifyUser");
        userModify.setPassword("gsgfssfgsf");
        boolean result = instance.modifyUser(userModify);
        assertTrue(result);
    }

    /**
     * Test of removeUser method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        String username = "testPlanner";
        boolean result = instance.removeUser(username);
        assertTrue(result);
    }

    /**
     * Test of getAllUsers method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        ArrayList<User> result = instance.getAllUsers();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of isSite method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testIsSite() {
        System.out.println("isSite");
        Site site = siteMod;
        boolean result = instance.isSite(site);
        assertTrue(result);
    }

    /**
     * Test of createSite method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testCreateSite() {
        System.out.println("createSite");
        boolean result = instance.createSite(siteCreate);
        assertTrue(result);
    }

    /**
     * Test of modifySite method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testModifySite() {
        System.out.println("modifySite");
        Site oldSite = siteMod;
        String department = "testDepartmentMod";
        boolean result = instance.modifySite(oldSite, department);
        siteMod.setDepartment(department);
        assertTrue(result);
    }

    /**
     * Test of removeSite method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testRemoveSite() {
        System.out.println("removeSite");
        Site siteRemove = site;
        boolean result = instance.removeSite(site);
        assertTrue(result);
    }

    /**
     * Test of getAllSites method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetAllSites() {
        System.out.println("getAllSites");
        ArrayList<Site> result = instance.getAllSites();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of isTypology method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testIsTypology() {
        System.out.println("isTypology");
        boolean result = instance.isTypology(isType);
        assertTrue(result);
    }

    /**
     * Test of createTypology method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testCreateTypology() {
        System.out.println("createTypology");
        boolean result = instance.createTypology(type);
        assertTrue(result);
    }

    /**
     * Test of modifyTypology method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testModifyTypology() {
        System.out.println("modifyTypology");
        String oldType = typeModify;
        String newType = otherTypology;
        boolean result;
        result = instance.modifyTypology(oldType, newType);
        assertTrue(result);
    }

    /**
     * Test of removeTypology method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testRemoveTypology() {
        System.out.println("removeTypology");
        boolean result = instance.removeTypology(type);
        assertTrue(result);
    }

    /**
     * Test of getAllTypologies method, of class SystemAdministratorDataAccess.
     */
    //@Ignore
    @Test
    public void testGetAllTypologies() {
        System.out.println("getAllTypologies");
        ArrayList<String> result = instance.getAllTypologies();
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of isProcedure method, of class SystemAdministratorDataAccess.
     */
    @Ignore
    //@Test
    public void testIsProcedure() {
        System.out.println("isProcedure");
        Procedure procedure = null;
        SystemAdministratorDataAccess instance = new SystemAdministratorDataAccess();
        boolean expResult = false;
        boolean result = instance.isProcedure(procedure);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createProcedure method, of class SystemAdministratorDataAccess.
     */
    @Ignore
    //@Test
    public void testCreateProcedure() {
        System.out.println("createProcedure");
        Procedure procedure = null;
        SystemAdministratorDataAccess instance = new SystemAdministratorDataAccess();
        boolean expResult = false;
        boolean result = instance.createProcedure(procedure);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyProcedure method, of class SystemAdministratorDataAccess.
     */
    @Ignore
    //@Test
    public void testModifyProcedure() {
        System.out.println("modifyProcedure");
        String oldName = "";
        String newName = "";
        SystemAdministratorDataAccess instance = new SystemAdministratorDataAccess();
        boolean expResult = false;
        boolean result = instance.modifyProcedure(oldName, newName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeProcedure method, of class SystemAdministratorDataAccess.
     */
    @Ignore
    //@Test
    public void testRemoveProcedure() {
        System.out.println("removeProcedure");
        String name = "";
        SystemAdministratorDataAccess instance = new SystemAdministratorDataAccess();
        boolean expResult = false;
        boolean result = instance.removeProcedure(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllProcedures method, of class SystemAdministratorDataAccess.
     */
    @Ignore
    //@Test
    public void testGetAllProcedures() {
        System.out.println("getAllProcedures");
        SystemAdministratorDataAccess instance = new SystemAdministratorDataAccess();
        ArrayList<Procedure> expResult = null;
        ArrayList<Procedure> result = instance.getAllProcedures();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
