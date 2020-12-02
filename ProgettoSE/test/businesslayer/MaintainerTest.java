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
public class MaintainerTest {

    Maintainer instance = new Maintainer("testMaintainer", "testMaintainer");
    static ArrayList<String> competencies = new ArrayList<String>();
    Maintainer instanceCompetencies;

    public MaintainerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        for(int i=0;i<6;i++){
            competencies.add("competencies"+i);
        }
        Maintainer instanceCompetencies = new Maintainer("testMaintainerCompetencies", "testMaintainerCompetencies",competencies);
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
        System.out.println("Get Username - Maintainer");
        String expResult = "testMaintainer";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPassword() {
        System.out.println("Get Password - Maintainer");
        String expResult = "testMaintainer";
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetUsername() {
        System.out.println("Set Username - Maintainer");
        String expResult = "testMaintainerSet";
        instance.setUsername("testMaintainerSet");
        assertEquals(expResult, instance.getUsername());
    }

    @Test
    public void testSetPassword() {
        System.out.println("Set Password - Maintainer");
        String expResult = "testMaintainerSet";
        instance.setUsername("testMaintainerSet");
        assertEquals(expResult, instance.getUsername());
    }

}
