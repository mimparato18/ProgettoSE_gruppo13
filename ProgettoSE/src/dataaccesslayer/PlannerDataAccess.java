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
import java.sql.SQLException;
import java.util.ArrayList;
import businesslayer.*;
import java.util.concurrent.TimeUnit;

public class PlannerDataAccess extends Database {

    public PlannerDataAccess() {
    }

    public boolean createActivity(MaintenanceActivity activity) {
        if (this.isActivity(activity.getId())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO "
                + "maintenanceactivity(materials, typology, estimatedtime, activitydescription, branchoffice, department, week, interruptible, workspacenotes) "
                + "values ('%s', '%s', '%d', '%s', '%s', '%s', '%d', %b, '%s')", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
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
                + "week = '%d', interruptible = %b, workspacenotes = '%s' WHERE id = '%d'", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
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
    
    public int nextId() throws SQLException, InterruptedException{
        int id = 0;
        statement.execute("ANALYZE TABLE maintenanceactivity");
        TimeUnit.MILLISECONDS.sleep(200);
        resultSet = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'progettosedb' AND TABLE_NAME = 'maintenanceactivity'");
        while (resultSet.next()) {
            id = resultSet.getInt(1);
        }
        return id;
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
    
    public ArrayList<String> getAllTypologies() {
        String type;
        ArrayList<String> typologiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM typology");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                type = resultSet.getString(1);
                typologiesList.add(type);
            }
            return typologiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<Site> getAllSites() {
        String branchOffice;
        String department;
        ArrayList<Site> sitesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM site");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                branchOffice = resultSet.getString(1);
                department = resultSet.getString(2);
                sitesList.add(new Site(branchOffice, department));
            }
            return sitesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
