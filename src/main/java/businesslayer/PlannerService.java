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

    private Planner planner;
    private MaintenanceActivityDaoImpl activityDao = new MaintenanceActivityDaoImpl();
    private TypologyDaoImpl typologyDao = new TypologyDaoImpl();
    private SiteDaoImpl siteDao = new SiteDaoImpl();

    public PlannerService(Planner planner) {
        this.planner = planner;
    }

    public boolean addActivity(String site, String typology, String description,
            int hours, int minutes, boolean interruptible, String materials, String week, String workspaceNotes) {

        return activityDao.insertActivity(planner.createActivity(new Site(site.split(" - ")[0], site.split(" - ")[1]), typology, description,
                (hours * 60 + minutes), interruptible, materials, Integer.parseInt(week), workspaceNotes));
    }

    public boolean updateActivity(int id, Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return activityDao.updateActivity(planner.createActivity(id, site, typology, description,
                interventionTime, interruptible, materials, week, workspaceNotes));
    }

    public boolean deleteActivity(int id) {

        return activityDao.deleteActivity(id);
    }

    public ArrayList<MaintenanceActivity> viewActivities() {

        return activityDao.getAllActivities();
    }

    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int week) {

        ArrayList<MaintenanceActivity> activities = activityDao.getAllActivities();
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

    public String getNextId() {

        try {
            return Integer.toString(activityDao.nextId());
        } catch (InterruptedException | SQLException ex) {
            return "err";
        }

    }

    public ArrayList<String> getAllSites() {
        ArrayList<Site> sites = siteDao.getAllSites();
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
        return typologyDao.getAllTypologies();
    }

}
