/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import StubClass.SystemAdministratorServiceStub;
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

    static SystemAdministratorServiceStub instance = new SystemAdministratorServiceStub(new SystemAdministrator("admin", "admin"));
    static ArrayList<String> competencies = new ArrayList<>();

    public SystemAdministratorServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        //for all view
        for (int i = 0; i < 6; i++) {
            competencies.add("Competence" + i);
        }
        for (int i = 0, k = 1; i < 6; i++, k++) {
            instance.addUser("Planner" + k, "password" + k, "Planner");
        }
        for (int i = 0, k = 1; i < 6; i++, k++) {
            instance.addSite("BranchOffice" + k, "Department" + k);
        }
        for (int i = 0, k = 1; i < 6; i++, k++) {
            instance.addTypology("Typology" + k);
        }
        for (int i = 0, k = 1; i < 6; i++, k++) {
            instance.addProcedure("Procedure" + k, competencies);
        }

        //for update
        instance.addUser("testUpdateUser", "passNoMod", "Planner");
        instance.addSite("BrachOfficeUpdate", "depNoMod");
        instance.addProcedure("noModProcedure", competencies);
        instance.addCompetence("noModCompetence");
        instance.addTypology("noModTypology");

        //for delete
        instance.addSite("testDeleteSite", "testDeleteSite");
        instance.addProcedure("testDeleteProcedure", competencies);
        instance.addCompetence("testDeleteCompetence");
        instance.addTypology("testDeleteTypology");

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
     * Test of addUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddUser_3args() {
        System.out.println("addUser - Planner");
        String username = "testAddUser3arg";
        String password = "testAddUser";
        String role = "Planner";
        assertTrue(instance.addUser(username, password, role));
    }

    /**
     * Test of addUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddUser_4args() {
        System.out.println("addUser - Maintainer");
        String username = "testAddUser4arg";
        String password = "testAddUser";
        String role = "Maintainer";
        assertTrue(instance.addUser(username, password, role, competencies));
    }

    /**
     * Test of updateUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateUser() {
        System.out.println("updateUser");
        String username = "testUpdateUser";
        String password = "passMod";
        String role = "Planner";
        assertTrue(instance.updateUser(username, password, role));
    }

    /**
     * Test of deleteUser method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        String username = "testAddUser4arg";
        assertTrue(instance.deleteUser(username));
    }

    /**
     * Test of viewUsers method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewUsers() {
        System.out.println("viewUsers");
        ArrayList<User> expResult = new ArrayList<>();
        ArrayList<User> result = instance.viewUsers();
        for (int i = 0, k = 1; i <= 6; i++, k++) {
            expResult.add(new Planner("Planner" + k, "password" + k));
        }
        for (int i = 0; i < result.size() - 2; i++) {
            assertEquals(expResult.get(i).getUsername(), result.get(i).getUsername());
            assertEquals(expResult.get(i).getPassword(), result.get(i).getPassword());
        }
    }

    /**
     * Test of addSite method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddSite() {
        System.out.println("addSite");
        String branchOffice = "testAddSite";
        String department = "testAddSite";
        assertTrue(instance.addSite(branchOffice, department));

    }

    /**
     * Test of updateSite method, of class SystemAdministratorService.
     */
    @Ignore
    //@Test
    public void testUpdateSite() {
        System.out.println("updateSite");
        Site oldSite = new Site("BrachOfficeUpdate", "depNoMod");
        String department = "depMod";
        assertTrue(instance.updateSite(oldSite, department));
    }

    /**
     * Test of deleteSite method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteSite() {
        System.out.println("deleteSite");
        Site site = new Site("testDeleteSite", "testDeleteSite");
        instance.addSite(site.getBranchOffice(), site.getDepartment());
        for (var siteOcc : instance.viewSites()) {
            System.out.println(siteOcc.getBranchOffice() + " " + siteOcc.getDepartment() + "\n");
        }
        assertTrue(instance.deleteSite(site));
    }

    /**
     * Test of viewSites method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewSites() {
        System.out.println("viewSites");
        ArrayList<Site> expResult = new ArrayList<>();
        ArrayList<Site> result = instance.viewSites();
        for (int i = 1; i < 8; i++) {
            expResult.add(new Site("BranchOffice" + i, "Department" + i));
        }
        for (int i = 0; i < result.size() - 3; i++) {
            assertEquals(expResult.get(i).getBranchOffice(), result.get(i).getBranchOffice());
            assertEquals(expResult.get(i).getDepartment(), result.get(i).getDepartment());
        }
    }

    /**
     * Test of addTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddTypology() {
        System.out.println("addTypology");
        String typology = "testAddTypology";
        assertTrue(instance.addTypology(typology));
    }

    /**
     * Test of updateTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateTypology() {
        System.out.println("updateTypology");
        String oldTypology = "noModTypology";
        String newTypology = "modTypology";
        assertTrue(instance.updateTypology(oldTypology, newTypology));
    }

    /**
     * Test of deleteTypology method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteTypology() {
        System.out.println("deleteTypology");
        String typology = "testDeleteTypology";
        assertTrue(instance.deleteTypology(typology));
    }

    /**
     * Test of viewTypologies method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewTypologies() {
        System.out.println("viewTypologies");
        ArrayList<String> expResult = new ArrayList<>();
        ArrayList<String> result = instance.viewTypologies();
        for (int i = 0, k = 1; i <= 6; i++, k++) {
            expResult.add("Typology" + k);
        }
        for (int i = 0; i < result.size() - 2; i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }

    /**
     * Test of addProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddProcedure() {
        System.out.println("addProcedure");
        String name = "testAddProcedure";
        assertTrue(instance.addProcedure(name, competencies));
    }

    /**
     * Test of updateProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateProcedure() {
        System.out.println("updateProcedure");
        String oldName = "noModProcedure";
        String newName = "ModProcedure";
        assertTrue(instance.updateProcedure(oldName, newName));
    }

    /**
     * Test of deleteProcedure method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteProcedure() {
        System.out.println("deleteProcedure");
        String name = "testDeleteProcedure";
        assertTrue(instance.deleteProcedure(name));
    }

    /**
     * Test of viewProcedures method, of class SystemAdministratorService.
     */
    @Ignore
    //@Test
    public void testViewProcedures() {
        System.out.println("viewProcedures");
        ArrayList<Procedure> expResult = null;
        ArrayList<Procedure> result = instance.viewProcedures();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompetence method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testAddCompetence() {
        System.out.println("addCompetence");
        String competence = "testAddCompetence";
        assertTrue(instance.addCompetence(competence));
    }

    /**
     * Test of updateCompetence method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testUpdateCompetence() {
        System.out.println("updateCompetence");
        String oldCompetence = "noModCompetence";
        String newCompetence = "modCompetence";
        assertTrue(instance.updateCompetence(oldCompetence, newCompetence));
    }

    /**
     * Test of deleteCompetence method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testDeleteCompetence() {
        System.out.println("deleteCompetence");
        String competence = "testDeleteCompetence";
        assertTrue(instance.deleteCompetence(competence));
    }

    /**
     * Test of viewCompetencies method, of class SystemAdministratorService.
     */
    //@Ignore
    @Test
    public void testViewCompetencies() {
        System.out.println("viewCompetencies");
        ArrayList<String> expResult = new ArrayList<>();
        ArrayList<String> result = instance.viewCompetencies();
        for (int i = 0; i <= 6; i++) {
            expResult.add("Competence" + i);
        }
        for (int i = 0; i < result.size() - 2; i++) {
            assertEquals(expResult.get(i), result.get(i));
        }
    }

}
