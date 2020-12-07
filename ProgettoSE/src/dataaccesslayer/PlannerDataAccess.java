/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

/**
 *
 * @author simo5
 */
import businesslayer.Procedure;
import java.sql.SQLException;
import java.util.ArrayList;
import businesslayer.*;

public class PlannerDataAccess extends Database {

    public PlannerDataAccess() {
    }

    public boolean createActivity(MaintenanceActivity activity) {
        if (this.isActivity(activity.getId())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO "
                + "maintenanceactivity(materials, typology, estimatedtime, activitydescription, branchoffice, department, week, interruptable, workspacenotes) "
                + "values ('%s', '%s', '%d', '%s', '%s', '%s', '%d', '%B', '%s')", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
                activity.getSite().getBranchOffice(), activity.getSite().getDepartment(), activity.getWeek(), activity.isInterruptible(), activity.getWorkspaceNotes());
        try {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean modifyActivity(int id, MaintenanceActivity activity) {
        if (!this.isActivity(id)) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintenanceactivity SET materials = '%s', typology = '%s', estimatedtime = '%d', activitydescription = '%s', branchoffice = '%s', department = '%s', "
                + "week = '%d', interruptable = '%B', workspacenotes = '%s' WHERE id = '%d'", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
                activity.getSite().getBranchOffice(), activity.getSite().getDepartment(), activity.getWeek(), activity.isInterruptible(), activity.getWorkspaceNotes(), id);
        try {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean removeActivity(int id) {
        if (!this.isActivity(id)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintenanceactivity WHERE id = '%d'", id);
        try {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<MaintenanceActivity> getAllActivities() {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes;
        int id, interventionTime, week;
        boolean interruptible;

        ArrayList<MaintenanceActivity> activitiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM maintenanceactivity");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                materials = resultSet.getString(2);
                typology = resultSet.getString(3);
                interventionTime = resultSet.getInt(4);
                activityDescription = resultSet.getString(5);
                branchOffice = resultSet.getString(6);
                department = resultSet.getString(7);
                week = resultSet.getInt(8);
                interruptible = resultSet.getBoolean(9);
                workspaceNotes = resultSet.getString(10);

                Site site = new Site(branchOffice, department);

                activitiesList.add(new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes));
            }
            return activitiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}