/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

/**
 *
 * @author marku
 */
public class MaintainerAvailability {

    private String maintainer;
    private int week;
    private int day;
    private int[] hours = new int[7];

    public MaintainerAvailability(String maintainer, int week, int day, int[] hours) {
        this.maintainer = maintainer;
        this.week = week;
        this.day = day;
        this.hours = hours;

    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int[] getHours() {
        return hours;
    }

    public void setHours(int[] hours) {
        this.hours = hours;
    }

}
