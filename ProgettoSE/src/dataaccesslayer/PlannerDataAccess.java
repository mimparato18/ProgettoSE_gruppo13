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

public class PlannerDataAccess extends Database{

    public PlannerDataAccess() {
    }
    
    public boolean isActivity(int id) {
        String selectQuery = String.format("SELECT id FROM maintenanceactivity WHERE (id = '%d')", id);
        int checkID = 0;
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                checkID = resultSet.getInt(1);
            }
            return (checkID != 0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean createActivity(Activity activity) {
        if (this.isActivity(activity.getId())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO maintenanceactivity(materials, typology, estimatedtime, activitydescription, branchoffice, department, week, interruptable, workspacenote) "
                + "values ('%s', '%s', '%d', '%s', '%s', '%s', '%d', '%B', '%s')", activity.getMaterials(), activity.getTypology(), activity.getEstimatedTime(), activity.getDescription(), 
                activity.getSite().getBranchOffice, activity.getSite().getDepartment(), activity.getWeek(), activity.getInterruptable(), activity.getWorkspaceNote());
        try {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean modifyActivity(int id, Activity activity) {
        if (!this.isActivity(id)) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintenanceactivity SET materials = '%s', typology = '%s', estimatedtime = '%d', activitydescription = '%s', branchoffice = '%s', department = '%s', "
                + "week = '%d', interruptable = '%B', workspacenote = '%s' WHERE id = '%d'", activity.getMaterials(), activity.getTypology(), activity.getEstimatedTime(), activity.getDescription(), 
                activity.getSite().getBranchOffice, activity.getSite().getDepartment(), activity.getWeek(), activity.getInterruptable(), activity.getWorkspaceNote(), id);
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

    public ArrayList<Activity> getAllActivities() {
        String materials, typology, activityDescription, branchOffice, department, workspacenote;
        int id, estimatedTime, week;
        boolean interruptable;
        
        ArrayList<Activity> activitiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM maintenanceactivity");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                id = resultSet.getInt(1);
                materials = resultSet.getString(2);
                typology = resultSet.getString(3);
                estimatedTime = resultSet.getInt(4);
                activityDescription = resultSet.getString(5);
                branchOffice = resultSet.getString(6);
                department = resultSet.getString(7);
                week = resultSet.getInt(8);
                interruptable = resultSet.getBoolean(9);
                workspacenote = resultSet.getString(10);
                
                Site site = new Site(branchOffice,department);
                
                activitiesList.add(new Activity(id, materials, typology, estimatedTime, activityDescription, site, week, interruptable, workspacenote));
            }
            return activitiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
}
