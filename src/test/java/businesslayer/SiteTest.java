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
public class SiteTest {

    Site instance = new Site("testFactorySite", "testDepartment");

    public SiteTest() {
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
     * Test of getBranchOffice method, of class Site.
     */
    @Test
    public void testGetBranchOffice() {
        System.out.println("getBranchOffice");
        String expResult = "testFactorySite";
        String result = instance.getBranchOffice();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBranchOffice method, of class Site.
     */
    @Test
    public void testSetBranchOffice() {
        System.out.println("setBranchOffice");
        String expResult = "testFactorySite";
        instance.setBranchOffice("testFactorySite");
        assertEquals(expResult, instance.getBranchOffice());
    }

    /**
     * Test of getDepartment method, of class Site.
     */
    @Test
    public void testGetDepartment() {
        System.out.println("getDepartment");
        String expResult = "testDepartment";
        String result = instance.getDepartment();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDepartment method, of class Site.
     */
    @Test
    public void testSetDepartment() {
        System.out.println("setDepartment");
        String expResult = "testDepartment";
        instance.setDepartment("testDepartment");
        assertEquals(expResult, instance.getDepartment());
    }

}
