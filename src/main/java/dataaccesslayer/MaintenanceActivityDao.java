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
    public MaintenanceActivity getActivityById(int id);
    public int nextId() throws SQLException, InterruptedException;
    public ArrayList<MaintenanceActivity> getActivitiesByWeek(int week);
    public ArrayList<MaintenanceActivity> getAllActivities();
    public boolean insertActivity(MaintenanceActivity activity);
    public boolean updateActivity(MaintenanceActivity activity);
    public boolean deleteActivity(int id);
}
