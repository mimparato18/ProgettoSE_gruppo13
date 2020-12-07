/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.*;
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

    public boolean addActivity(Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return db.createActivity(planner.createActivity(site, typology, description,
                interventionTime, interruptible, materials, week, workspaceNotes));
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

}
