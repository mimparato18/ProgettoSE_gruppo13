/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.MaintenanceActivity;
import businesslayer.Procedure;
import businesslayer.Site;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author marku
 */
public class MaintenanceActivityDaoImpl implements MaintenanceActivityDao {

    private ConnectionPool pool;

    public MaintenanceActivityDaoImpl() {
        pool = ConnectionPool.getPool();
    }
    /**
    * Check if the activity exists in the database
    *
    * @param   id   id of the activity to check
    * @return  true if the activity exists, false otherwise
    * 
    */
    private boolean isActivity(int id) {
        String selectQuery = String.format("SELECT id FROM maintenanceactivity WHERE (id = '%d')", id);
        int checkID = 0;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery);) {

            while (resultSet.next()) {
                checkID = resultSet.getInt(1);
            }
            return (checkID != 0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertActivity(MaintenanceActivity activity) {

        String insertQuery = String.format("INSERT INTO "
                + "maintenanceactivity(materials, typology, estimatedtime, activitydescription, branchoffice, department, week, interruptible, workspacenotes) "
                + "values ('%s', '%s', '%d', '%s', '%s', '%s', '%d', %b, '%s')", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
                activity.getSite().getBranchOffice(), activity.getSite().getDepartment(), activity.getWeek(), activity.isInterruptible(), activity.getWorkspaceNotes());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateActivity(MaintenanceActivity activity) {
        if (!this.isActivity(activity.getId())) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintenanceactivity SET materials = '%s', typology = '%s', estimatedtime = '%d', activitydescription = '%s', branchoffice = '%s', department = '%s', "
                + "week = '%d', interruptible = %b, workspacenotes = '%s' WHERE id = '%d'", activity.getMaterials(), activity.getTypology(), activity.getInterventionTime(), activity.getDescription(),
                activity.getSite().getBranchOffice(), activity.getSite().getDepartment(), activity.getWeek(), activity.isInterruptible(), activity.getWorkspaceNotes(), activity.getId());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteActivity(int id) {
        if (!this.isActivity(id)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintenanceactivity WHERE id = '%d'", id);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public MaintenanceActivity getActivityById(int selectedId) {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int id, interventionTime, week;
        boolean interruptible;

        MaintenanceActivity activity = null;
        String selectQuery = String.format("SELECT * FROM maintenanceactivity WHERE id = %d", selectedId);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery);) {

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                materials = resultSet.getString(2);
                typology = resultSet.getString(3);
                interventionTime = resultSet.getInt(4);
                activityDescription = resultSet.getString(5);
                branchOffice = resultSet.getString(6);
                department = resultSet.getString(7);
                procedureName = resultSet.getString(8);
                week = resultSet.getInt(9);
                interruptible = resultSet.getBoolean(10);
                workspaceNotes = resultSet.getString(11);

                Site site = new Site(branchOffice, department);

                if (procedureName != null) {
                    activity = new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes);
                } else {
                    activity = new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes);
                }
            }
            return activity;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int selectedWeek) {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int id, interventionTime, week;
        boolean interruptible;

        ArrayList<MaintenanceActivity> activitiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM maintenanceactivity WHERE week = %d", selectedWeek);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery);) {

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                materials = resultSet.getString(2);
                typology = resultSet.getString(3);
                interventionTime = resultSet.getInt(4);
                activityDescription = resultSet.getString(5);
                branchOffice = resultSet.getString(6);
                department = resultSet.getString(7);
                procedureName = resultSet.getString(8);
                week = resultSet.getInt(9);
                interruptible = resultSet.getBoolean(10);
                workspaceNotes = resultSet.getString(11);

                Site site = new Site(branchOffice, department);

                if (procedureName != null) {
                    activitiesList.add(new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes));
                } else {
                    activitiesList.add(new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes));
                }
            }
            return activitiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<MaintenanceActivity> getAllActivities() {
        String materials, typology, activityDescription, branchOffice, department, workspaceNotes, procedureName;
        int id, interventionTime, week;
        boolean interruptible;

        ArrayList<MaintenanceActivity> activitiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM maintenanceactivity");
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery);) {

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                materials = resultSet.getString(2);
                typology = resultSet.getString(3);
                interventionTime = resultSet.getInt(4);
                activityDescription = resultSet.getString(5);
                branchOffice = resultSet.getString(6);
                department = resultSet.getString(7);
                procedureName = resultSet.getString(8);
                week = resultSet.getInt(9);
                interruptible = resultSet.getBoolean(10);
                workspaceNotes = resultSet.getString(11);

                Site site = new Site(branchOffice, department);

                if (procedureName != null) {
                    activitiesList.add(new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, new Procedure(procedureName, null), week, workspaceNotes));
                } else {
                    activitiesList.add(new MaintenanceActivity(id, site, typology, activityDescription, interventionTime, interruptible, materials, week, workspaceNotes));
                }
            }
            return activitiesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int nextId() throws SQLException, InterruptedException {
        int id = 0;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            ResultSet resultSet = null;
            statement.execute("ANALYZE TABLE maintenanceactivity");
            TimeUnit.MILLISECONDS.sleep(200);
            resultSet = statement.executeQuery("SELECT `AUTO_INCREMENT` FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'progettosedb' AND TABLE_NAME = 'maintenanceactivity'");
            while (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            return id;
        }
    }

}
