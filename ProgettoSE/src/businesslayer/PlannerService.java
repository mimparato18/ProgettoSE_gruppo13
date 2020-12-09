/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class PlannerService {

    private PlannerDataAccess db;
    private Planner planner;

    public PlannerService(Planner planner) {
        db = new PlannerDataAccess();
        db.connect();
        this.planner = planner;
    }

    public boolean addActivity(String site, String typology, String description,
            int hours, int minutes, boolean interruptible, String materials, String week, String workspaceNotes) {

        return db.createActivity(planner.createActivity(new Site(site.split(" - ")[0], site.split(" - ")[1]), typology, description,
                (hours * 60 + minutes), interruptible, materials, Integer.parseInt(week), workspaceNotes));
    }

    public boolean updateActivity(int id, Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return db.modifyActivity(id, planner.createActivity(site, typology, description,
                interventionTime, interruptible, materials, week, workspaceNotes));
    }

    public boolean deleteActivity(int id) {

        return db.removeActivity(id);
    }

    public ArrayList<MaintenanceActivity> viewActivities() {

        return db.getAllActivities();
    }

    public ArrayList<MaintenanceActivity> getActivtiesByWeek(int week) {

        ArrayList<MaintenanceActivity> activities = db.getAllActivities();
        ArrayList<MaintenanceActivity> activitiesByWeek = new ArrayList<>();

        if (activities != null) {
            for (MaintenanceActivity a : activities) {
                if (a.getWeek() == week) {
                    activitiesByWeek.add(a);
                }
            }
        }

        return activitiesByWeek;
    }

    public ArrayList<String> getAllSites() {
        ArrayList<Site> sites = db.getAllSites();
        ArrayList<String> returnSites = new ArrayList<>();
        if (sites != null) {
            for (Site s : sites) {
                returnSites.add(s.getBranchOffice() + " - " + s.getDepartment());
            }
            return returnSites;
        }
        return null;
    }

    public ArrayList<String> getAllTypologies() {
        return db.getAllTypologies();
    }

    public String getNextId() {

        try {
            return Integer.toString(db.nextId());
        } catch (InterruptedException | SQLException ex) {
            return "err";
        }

    }

}
