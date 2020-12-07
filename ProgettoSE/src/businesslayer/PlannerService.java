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

    public boolean addActivity() {
        return true;
    }

    public boolean updateActivity() {
        return true;
    }

    public boolean deleteActivity() {
        return true;
    }

    public ArrayList<MaintenanceActivity> viewActivities() {
        return null;
    }

}
