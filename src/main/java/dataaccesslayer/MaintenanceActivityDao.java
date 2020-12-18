/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.MaintenanceActivity;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public interface MaintenanceActivityDao {

    /**
     * Get the activity specified by the id
     *
     * @param id the id of the activity to find
     * @return a MaintenanceActivity
     */
    public MaintenanceActivity getActivityById(int id);

    /**
     * Get the id of the next activity
     *
     * @return the id of the next activity
     *
     * @throws SQLException
     * @throws InterruptedException
     */
    public int nextId() throws SQLException, InterruptedException;

    /**
     * Get all activities assigned to a specified week
     *
     * @param week the week in which the activity needs to be assigned to be
     * returned
     * @return ArrayList of MaintenanceActivity
     */
    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int week);

    /**
     * Get all activities
     *
     * @return ArrayList of MaintenanceActivity
     */
    public ArrayList<MaintenanceActivity> getAllActivities();

    /**
     * Insert a activity inside the database
     *
     * @param activity the activity to insert in the database
     * @return true if successful, false otherwise
     */
    public boolean insertActivity(MaintenanceActivity activity);

    /**
     * Update a activity inside the database
     *
     * @param activity the activity to update in the database
     * @return true if successful, false otherwise
     */
    public boolean updateActivity(MaintenanceActivity activity);

    /**
     * Delete a activity from the database
     *
     * @param id of the activity to delete from the database
     * @return true if successful, false otherwise
     */
    public boolean deleteActivity(int id);
}
