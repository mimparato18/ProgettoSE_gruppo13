/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.MaintainerAvailability;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author marku
 */
public interface MaintainerAvailabilityDao {
    //CRUD
    /**
    * Insert a MaintainerAvailability in the database
    *
    * @param  ava  MaintainerAvailability
    * @return   true if successfull, false otherwise
    */
    public boolean insertMaintainerAvailability(MaintainerAvailability ava);
    /**
    * Update a MaintainerAvailability in the database
    *
    * @param  ava  MaintainerAvailability
    * @return   true if successfull, false otherwise
    */
    public boolean updateMaintainerAvailability(MaintainerAvailability ava);
    /**
    * Delete a MaintainerAvailability from the database
    *
    * @param  ava  MaintainerAvailability
    * @return   true if successfull, false otherwise
    */
    public boolean deleteMaintainerAvailability(MaintainerAvailability ava);
    /**
    * Get all the MaintainerAvailabilities associated to a specified week and username
    *
    * @param  week      week in which the activity is assigned
    * @param  username  username of the maintainer to check its competencies
    * @return   ArrayList of MaintainerAvailability
    * 
    * @throws SQLException
    */
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeek(int week, String username)throws SQLException;
    /**
    * Get all the MaintainerAvailabilities associated to a specified week, username and day
    *
    * @param  week          week in which the activity is assigned
    * @param  username      username of the maintainer to check its competencies
    * @param  day           day in which the activity is assigned
    * @return   ArrayList of MaintainerAvailability
    */
    public MaintainerAvailability getMaintainerAvailabilitiesByWeekAndDay(int week, String username, int day);

}
