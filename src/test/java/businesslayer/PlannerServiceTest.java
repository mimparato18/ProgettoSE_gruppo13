/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import StubClass.PlannerServiceStub;
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
public class PlannerServiceTest {
    
    static PlannerServiceStub instance = new PlannerServiceStub(new Planner("PlannerServiceTest", "PlannerServiceTest"));
    static MaintenanceActivity instanceAct;
    static SystemAdministratorServiceStub instanceSA = new SystemAdministratorServiceStub(new SystemAdministrator("admin", "admin"));
    static ArrayList<String> allUser;
    static ArrayList<String> allSite;
    static ArrayList<String> allTypology;
    
    public PlannerServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Site site = new Site("BranchOffice", "Department");
        String typology = "typology";
        String description = "BLA BLA BLA BLA";
        int hours = 1;
        int minutes = 33;
        int interventionTime = hours * 60 + minutes;
        boolean interruptible = false;
        String materials = "materials1 materials2 materials3";
        int week = 7;
        String workspaceNotes = "BLA BLA BLA";
        
        instanceAct = new MaintenanceActivity(site, typology, description, interventionTime, interruptible, materials, week, workspaceNotes);
        instance.addActivity(instanceAct.getSite().getBranchOffice() + " - " + instanceAct.getSite().getDepartment(), instanceAct.getTypology(), instanceAct.getDescription(), hours, minutes, interruptible, instanceAct.getMaterials(), instanceAct.getWeek() + "", instanceAct.getWorkspaceNotes());
        
        allUser = new ArrayList<>();
        allSite = new ArrayList<>();
        allTypology = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            instanceSA.addUser("Planner" + i, "password" + i, "Planner");
            instanceSA.addSite("BranchOffice" + i, "Department" + i);
            instanceSA.addTypology("Typology" + i);
            allUser.add("Planner" + i + " - " + "password" + i + " - " + "Planner");
            allSite.add("BranchOffice" + i + " - Department" + i);
            allTypology.add("Typology" + i);
        }
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
        String site = instanceAct.getSite().getBranchOffice() + "Add - " + instanceAct.getSite().getDepartment();
        String typology = instanceAct.getTypology() + "2";
        String description = "addActivity";
        int hours = 0;
        int minutes = 52;
        boolean interruptible = false;
        String materials = "addActivity";
        String week = instanceAct.getWeek() + "";
        String workspaceNotes = instanceAct.getWorkspaceNotes();
        boolean result = instance.addActivity(site, typology, description, hours, minutes, interruptible, materials, week, workspaceNotes);
        assertTrue(result);
    }

    /**
     * Test of updateActivity method, of class PlannerService.
     */
    @Test
    public void testUpdateActivity() {
        System.out.println("updateActivity");
        
        Site site = instanceAct.getSite();
        String typology = instanceAct.getTypology();
        String description = instanceAct.getDescription();
        int interventionTime = instanceAct.getInterventionTime();
        boolean interruptible = false;
        String materials = instanceAct.getMaterials() + " MATERIALS4";
        int week = instanceAct.getWeek();
        String workspaceNotes = instanceAct.getWorkspaceNotes();
        
        boolean result = instance.updateActivity(1, site, typology, description, interventionTime, interruptible, materials, week, workspaceNotes);
        assertTrue(result);
    }

    /**
     * Test of deleteActivity method, of class PlannerService.
     */
    @Test
    public void testDeleteActivity() {
        System.out.println("deleteActivity");
        int id = 1;
        boolean result = instance.deleteActivity(id);
        assertTrue(result);
    }

    /**
     * Test of viewActivities method, of class PlannerService.
     */
    @Test
    public void testViewActivities() {
        System.out.println("viewActivities");
        ArrayList<MaintenanceActivity> expResult = new ArrayList<>();
        
        expResult.add(new MaintenanceActivity(1, instanceAct.getSite(), instanceAct.getTypology(), instanceAct.getDescription(), (1 * 60 + 33), false, instanceAct.getMaterials() + " MATERIALS4", instanceAct.getWeek(), instanceAct.getWorkspaceNotes()));
        
        ArrayList<MaintenanceActivity> result = instance.viewActivities();
        
        assertEquals(expResult.get(0).getId(), result.get(0).getId());
        assertEquals(expResult.get(0).getDescription(), result.get(0).getDescription());
        assertEquals(expResult.get(0).getInterventionTime(), result.get(0).getInterventionTime());
        assertEquals(expResult.get(0).getMaterials(), result.get(0).getMaterials());
        assertEquals(expResult.get(0).getSite().getBranchOffice(), result.get(0).getSite().getBranchOffice());
    }

    /**
     * Test of getActivitiesByWeek method, of class PlannerService.
     */
    @Ignore
    //@Test
    public void testGetActivitiesByWeek() {
        System.out.println("getActivitiesByWeek");
        int week = 7;
        ArrayList<MaintenanceActivity> expResult = new ArrayList<>();
        
        expResult.add(new MaintenanceActivity(1, instanceAct.getSite(), instanceAct.getTypology(), instanceAct.getDescription(), (1 * 60 + 33), false, instanceAct.getMaterials() + " MATERIALS4", instanceAct.getWeek(), instanceAct.getWorkspaceNotes()));
        ArrayList<MaintenanceActivity> result = instance.getActivitiesByWeek(week);
        assertEquals(expResult.get(0).getWeek(), 7);
        assertEquals(7, result.get(0).getWeek());
        assertEquals(expResult.get(0).getId(), result.get(0).getId());
    }

    /**
     * Test of getNextId method, of class PlannerService.
     */
    //@Ignore
    @Test
    public void testGetNextId() {
        System.out.println("getNextId");
        String expResult = "3";
        String result = instance.getNextId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllSites method, of class PlannerService.
     */
    //@Ignore
    @Test
    public void testGetAllSites() {
        System.out.println("getAllSites");
        ArrayList<String> expResult = allSite;
        ArrayList<String> result = instance.getAllSites();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAllTypologies method, of class PlannerService.
     */
    //@Ignore
    @Test
    public void testGetAllTypologies() {
        System.out.println("getAllTypologies");
        ArrayList<String> expResult = allTypology;
        ArrayList<String> result = instance.getAllTypologies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAvailabilityByWeek method, of class PlannerService.
     */
    @Ignore
    //@Test
    public void testGetAvailabilityByWeek() {
        System.out.println("getAvailabilityByWeek");
        int activityId = 0;
        int week = 0;
        ArrayList<MaintainerAvailabilityDto> expResult = null;
        ArrayList<MaintainerAvailabilityDto> result = instance.getAvailabilityByWeek(activityId, week);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
        
    }

    /**
     * Test of getAvailabilityByWeekAndDay method, of class PlannerService.
     */
    @Test
    public void testGetAvailabilityByWeekAndDay() {
        System.out.println("getAvailabilityByWeekAndDay");
        int week = 7;
        String username = "Mario";
        String dayname = "Mon";
        ArrayList<String> expResult = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            expResult.add("60 min");
        }
        ArrayList<String> result = instance.getAvailabilityByWeekAndDay(week, username, dayname);
        assertEquals(expResult, result);
    }

    /**
     * Test of getDayOfMonth method, of class PlannerService.
     */
    @Ignore
    //@Test
    public void testGetDayOfMonth() {
        System.out.println("getDayOfMonth");
        String dayname = "";
        int week = 0;
        int expResult = 0;
        int result = instance.getDayOfMonth(dayname, week);
        assertEquals(expResult, result);
    }
    
}
