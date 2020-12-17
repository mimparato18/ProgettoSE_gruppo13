/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marco Calabrese
 */
public class MaintainerAvailabilityTest {

    static MaintainerAvailability instance;
    static MaintainerAvailability instanceForSet;
    static private String maintainerForSet;
    static private int weekForSet;
    static private int dayForSet;
    static private int[] hoursForSet = new int[7];
    static private int[] hours = new int[7];

    public MaintainerAvailabilityTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        for (int i = 0; i < 7; i++) {
            hours[i] = (int) (Math.random() * (7 - 0 + 1) + 0);
        }
        instance = new MaintainerAvailability("Mario", 7, 13, hours);

        //For set method
        maintainerForSet = "setOK";
        weekForSet = 50;
        dayForSet = 30;
        hoursForSet[0] = 3;
        hoursForSet[1] = 6;
        hoursForSet[2] = 5;
        hoursForSet[3] = 2;
        hoursForSet[4] = 7;
        hoursForSet[5] = 4;
        hoursForSet[6] = 2;
        instanceForSet = new MaintainerAvailability("ForSetMethod", 10, 8, hours);
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
     * Test of getMaintainer method, of class MaintainerAvailability.
     */
    @Test
    public void testGetMaintainer() {
        System.out.println("getMaintainer");
        String expResult = "Mario";
        String result = instance.getMaintainer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMaintainer method, of class MaintainerAvailability.
     */
    @Test
    public void testSetMaintainer() {
        System.out.println("setMaintainer");
        String maintainer = maintainerForSet;
        instance.setMaintainer(maintainer);
        assertEquals(maintainerForSet, instance.getMaintainer());
    }

    /**
     * Test of getWeek method, of class MaintainerAvailability.
     */
    @Test
    public void testGetWeek() {
        System.out.println("getWeek");
        int expResult = 7;
        int result = instance.getWeek();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWeek method, of class MaintainerAvailability.
     */
    @Test
    public void testSetWeek() {
        System.out.println("setWeek");
        int week = weekForSet;
        instance.setWeek(week);
        assertEquals(weekForSet, instance.getWeek());
    }

    /**
     * Test of getDay method, of class MaintainerAvailability.
     */
    @Test
    public void testGetDay() {
        System.out.println("getDay");
        int expResult = 13;
        int result = instance.getDay();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDay method, of class MaintainerAvailability.
     */
    @Test
    public void testSetDay() {
        System.out.println("setDay");
        int day = dayForSet;
        instance.setDay(day);
        assertEquals(dayForSet, instance.getDay());
    }

    /**
     * Test of getHours method, of class MaintainerAvailability.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        int[] expResult = hours;
        int[] result = instance.getHours();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of setHours method, of class MaintainerAvailability.
     */
    @Test
    public void testSetHours() {
        System.out.println("setHours");
        int[] hours = hoursForSet;
        instance.setHours(hours);
        Assert.assertArrayEquals(hoursForSet, instance.getHours());
    }

}
