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
import org.junit.Ignore;

/**
 *
 * @author Marco Calabrese
 */
public class SystemAdministratorServiceTest {

    static SystemAdministratorService instance = new SystemAdministratorService();
    static ArrayList<String> competence = new ArrayList<String>();
    static Maintainer modifyMaintainer = new Maintainer("ModMaintainer", "noPassWordMod", competence);
    static Planner modifyPlanner = new Planner("ModPlanner", "noPassWordMod");

    Site site = new Site("testFactorySite0", "testDepartment");
    static Site siteUpdate = new Site("testFactorySite1", "noTestDepartmentMod");

    static String TypologyDel = "delType";
    static String ProcedureDel = "delProc";

    public SystemAdministratorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        competence.add("competence1");
        competence.add("competence2");
        competence.add("competence3");
        boolean init = init = instance.addTypology(TypologyDel);
        init = instance.addUser(modifyMaintainer.getUsername(), modifyMaintainer.getPassword(), "Maintainer", "competence1\ncompetence2\ncompetence3");
        init = instance.addUser(modifyPlanner.getUsername(), modifyPlanner.getPassword(), "Planner");
        init = instance.addSite(siteUpdate.getBranchOffice(), siteUpdate.getDepartment());
        init = instance.addTypology("oldTypology");
        init = instance.addProcedure("oldName");
        init = instance.addProcedure(ProcedureDel);
    }

    @AfterClass
    public static void tearDownClass() {

        for (int i = 0; i < 6; i++) {
            instance.deleteTypology("Typology " + i);
            instance.deleteProcedure("Procedure " + i);
        }
        instance.deleteUser(modifyMaintainer.getUsername());
        instance.deleteUser(modifyPlanner.getUsername());
        instance.deleteSite(new Site(siteUpdate.getBranchOffice(), "testDepartmentMod"));
        instance.deleteTypology("newTypology");
        instance.deleteProcedure("newName");
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addUser method, of class SystemAdministratorService.
     */
    @Test
    public void testAddUser_3args() {
        System.out.println("addUser");
        for (int i = 0; i < 6; i++) {
            String username = "UsernameP " + i;
            String password = "passwordP " + i;
            String role = "Planner";
            boolean result = instance.addUser(username, password, role);
            assertTrue(result);
        }
    }

    /**
     * Test of addUser method, of class SystemAdministratorService.
     */
    @Test
    public void testAddUser_4args() {
        System.out.println("addUser");
        for (int i = 0; i < 6; i++) {
            String username = "UsernameM " + i;
            String password = "passwordM " + i;
            String role = "Maintainer";
            String competencies = "";
            for (int j = 0; j < 5; j++) {
                competencies = "competence " + i;
            }
            boolean result = instance.addUser(username, password, role, competencies);
            assertTrue(result);
        }
    }

    /**
     * Test of updateUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        boolean result1 = instance.updateUser(modifyPlanner.getUsername(), "passWordMod", "Planner");
        boolean result2 = instance.updateUser(modifyMaintainer.getUsername(), "passWordMod", "Maintainer");
        assertTrue(result1);
        assertTrue(result2);

    }

    /**
     * Test of deleteUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        for (int i = 0; i < 6; i++) {
            boolean result1 = instance.deleteUser("UsernameP " + i);
            boolean result2 = instance.deleteUser("UsernameM " + i);
            assertTrue(result1);
            assertTrue(result2);
        }

    }

    /**
     * Test of viewUsers method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewUsers() {
        System.out.println("viewUsers");
        ArrayList<User> result = instance.viewUsers();
        assertTrue(result != null);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of addSite method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddSite() {
        System.out.println("addSite");
        for (int i = 0; i < 6; i++) {
            String branchOffice = "FactorySite " + i;
            String department = "Department " + i;
            boolean result = instance.addSite(branchOffice, department);
            assertTrue(result);
        }
    }

    /**
     * Test of updateSite method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateSite() {
        System.out.println("updateSite");
        Site oldSite = siteUpdate;
        String department = "testDepartmentMod";
        boolean result = instance.updateSite(oldSite, department);
        assertTrue(result);
    }

    /**
     * Test of deleteSite method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteSite() {
        System.out.println("deleteSite");
        for (int i = 0; i < 6; i++) {
            boolean result = instance.deleteSite(new Site("FactorySite " + i, "Department " + i));
            assertTrue(result);
        }
    }

    /**
     * Test of viewSites method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewSites() {
        System.out.println("viewSites");
        ArrayList<Site> result = instance.viewSites();
        assertTrue(result != null);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of addTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddTypology() {
        System.out.println("addTypology");
        boolean result;
        for (int i = 0; i < 6; i++) {
            result = instance.addTypology("Typology " + i);
            assertTrue(result);
        }
    }

    /**
     * Test of updateTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateTypology() {
        System.out.println("updateTypology");
        String oldTypology = "oldTypology";
        String newTypology = "newTypology";
        boolean result = instance.updateTypology(oldTypology, newTypology);
        assertTrue(result);
    }

    /**
     * Test of deleteTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteTypology() {
        System.out.println("deleteTypology");
        boolean result = instance.deleteTypology(TypologyDel);
        assertTrue(result);
    }

    /**
     * Test of viewTypologies method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewTypologies() {
        System.out.println("viewTypologies");
        ArrayList<String> result = instance.viewTypologies();
        assertTrue(result != null);
        assertTrue(!result.isEmpty());
    }

    /**
     * Test of addProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddProcedure() {
        System.out.println("addProcedure");
        for (int i = 0; i < 6; i++) {
            String name = "Procedure " + i;
            boolean result = instance.addProcedure(name);
            assertTrue(result);
        }
    }

    /**
     * Test of updateProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateProcedure() {
        System.out.println("updateProcedure");
        String oldName = "oldName";
        String newName = "newName";
        boolean result = instance.updateProcedure(oldName, newName);
        assertTrue(result);
    }

    /**
     * Test of deleteProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteProcedure() {
        System.out.println("deleteProcedure");
        boolean result = instance.deleteProcedure(ProcedureDel);
        assertTrue(result);
    }

    /**
     * Test of viewProcedures method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewProcedures() {
        System.out.println("viewProcedures");
        ArrayList<Procedure> result = instance.viewProcedures();
        assertTrue(result != null);
        assertTrue(!result.isEmpty());
    }

}
