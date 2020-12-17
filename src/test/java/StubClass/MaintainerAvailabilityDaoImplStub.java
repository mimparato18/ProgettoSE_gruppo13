/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.MaintainerAvailability;
import dataaccesslayer.MaintainerAvailabilityDao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class MaintainerAvailabilityDaoImplStub implements MaintainerAvailabilityDao {

    ArrayList<String> allMaintainerAvailability;
    //username-week-day-hour1-hour2-hour3-hour4-hour5-hour6-hour7

    public MaintainerAvailabilityDaoImplStub() {
        this.allMaintainerAvailability = new ArrayList<>();
    }

    private boolean isAvailableByWeek(String username, int week) {

        for (var maintAva : allMaintainerAvailability) {
            String[] parts = maintAva.split("-");
            if (username.equals(parts[0]) && week == Integer.parseInt(parts[1])) {
                return true;
            }
        }
        return false;
    }

    private boolean isAvailableByDay(String username, int week, int day) {

        for (var maintAva : allMaintainerAvailability) {
            String[] parts = maintAva.split("-");
            if ((username.equals(parts[0])) && (week == Integer.parseInt(parts[1])) && (day == Integer.parseInt(parts[2]))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertMaintainerAvailability(MaintainerAvailability ava) {

        if (this.isAvailableByDay(ava.getMaintainer(), ava.getWeek(), ava.getDay())) {
            return false;
        }
        return allMaintainerAvailability.add(
                ava.getMaintainer() + "-" + ava.getWeek() + "-" + ava.getDay() + "-"
                + ava.getHours()[0] + "-" + ava.getHours()[1] + "-" + ava.getHours()[2] + "-" + ava.getHours()[3] + "-"
                + ava.getHours()[4] + "-" + ava.getHours()[5] + "-" + ava.getHours()[6]);
    }

    @Override
    public boolean updateMaintainerAvailability(MaintainerAvailability ava) {

        if (!this.isAvailableByDay(ava.getMaintainer(), ava.getWeek(), ava.getDay())) {
            return false;
        }
        for (int i = 0; i < allMaintainerAvailability.size(); i++) {
            int indexUpdate = allMaintainerAvailability.indexOf(i);
            String string = allMaintainerAvailability.get(i);
            String[] parts = string.split("-");
            if ((ava.getMaintainer().equals(parts[0])) && (ava.getWeek() == Integer.parseInt(parts[1])) && (ava.getDay() == Integer.parseInt(parts[2]))) {
                parts[3] = String.valueOf(ava.getHours()[0]);
                parts[4] = String.valueOf(ava.getHours()[1]);
                parts[5] = String.valueOf(ava.getHours()[2]);
                parts[6] = String.valueOf(ava.getHours()[3]);
                parts[7] = String.valueOf(ava.getHours()[4]);
                parts[8] = String.valueOf(ava.getHours()[5]);
                parts[9] = String.valueOf(ava.getHours()[6]);

                String updateString = parts[0] + "-" + parts[1] + "-" + parts[2] + "-" + parts[3] + "-" + parts[4] + "-" + parts[5] + "-" + parts[6] + "-" + parts[7] + "-" + parts[8] + "-" + parts[9];
                allMaintainerAvailability.add(indexUpdate, updateString);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteMaintainerAvailability(MaintainerAvailability ava) {

        if (!this.isAvailableByDay(ava.getMaintainer(), ava.getWeek(), ava.getDay())) {
            return false;
        }
        for (int i = 0; i < allMaintainerAvailability.size(); i++) {
            int indexDelete = allMaintainerAvailability.indexOf(i);
            String string = allMaintainerAvailability.get(i);
            String[] parts = string.split("-");
            if ((ava.getMaintainer().equals(parts[0])) && (ava.getWeek() == Integer.parseInt(parts[1])) && (ava.getDay() == Integer.parseInt(parts[2]))) {
                if (allMaintainerAvailability.remove(indexDelete) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeek(int week, String username) throws SQLException {
        int day;
        int[] hours = new int[7];
        ArrayList<MaintainerAvailability> avaList = new ArrayList<>();

        if (!this.isAvailableByWeek(username, week)) {
            return null;
        }

        for (int i = 0; i < allMaintainerAvailability.size(); i++) {
            String string = allMaintainerAvailability.get(i);
            String[] parts = string.split("-");
            if (username.equals(parts[0]) && week == Integer.parseInt(parts[1])) {
                username = parts[0];
                week = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
                for (int j = 0, k = 3; j < 7; j++, k++) {
                    hours[j] = Integer.parseInt(parts[k]);
                }
                MaintainerAvailability ava = new MaintainerAvailability(username, week, day, hours);
                avaList.add(ava);
            }
        }
        return avaList;
    }

    @Override
    public MaintainerAvailability getMaintainerAvailabilitiesByWeekAndDay(int week, String username, int day) {
        int[] hours = new int[7];
        MaintainerAvailability ava = null;

        if (!this.isAvailableByWeek(username, week)) {
            return null;
        }

        for (int i = 0; i < allMaintainerAvailability.size(); i++) {
            String string = allMaintainerAvailability.get(i);
            String[] parts = string.split("-");
            if (username.equals(parts[0]) && week == Integer.parseInt(parts[1]) && day == Integer.parseInt(parts[2])) {
                username = parts[0];
                week = Integer.parseInt(parts[1]);
                day = Integer.parseInt(parts[2]);
                for (int j = 0, k = 3; j < 7; j++, k++) {
                    hours[j] = Integer.parseInt(parts[k]);
                }
                ava = new MaintainerAvailability(username, week, day, hours);
            }
        }

        return ava;
    }
}
