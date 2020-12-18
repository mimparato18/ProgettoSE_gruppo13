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
public class ProcedureTest {

    static Procedure instance;
    static ArrayList<String> competencies = new ArrayList<String>();

    public ProcedureTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        for (int i = 0; i < 6; i++) {
            competencies.add("competencies" + i);
        }
        instance = new Procedure("testProcedure", competencies);
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
     * Test of getName method, of class Procedure.
     */
    @Test
    public void testGetName() {
        System.out.println("getName -  Procedure");
        String expResult = "testProcedure";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Procedure.
     */
    @Test
    public void testSetName() {
        System.out.println("setName - Procedure");
        String expResult = "testProcedureMod";
        instance.setName("testProcedureMod");
        assertEquals(expResult, instance.getName());
    }

    /**
     * Test of getCompetencies method, of class Procedure.
     */
    @Test
    public void testGetCompetencies() {
        System.out.println("getCompetencies - Procedure");
        ArrayList<String> expResult = competencies;
        ArrayList<String> result = instance.getCompetencies();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCompetencies method, of class Procedure.
     */
    @Test
    public void testSetCompetencies() {
        System.out.println("setCompetencies - Procedure");
        ArrayList<String> expResult = competencies;
        expResult.add("competencies 7");
        ArrayList<String> result = competencies;
        result.add("competencies 7");
        instance.setCompetencies(result);
        assertEquals(expResult, instance.getCompetencies());
    }

}
