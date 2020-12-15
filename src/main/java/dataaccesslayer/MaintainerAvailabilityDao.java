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
    public boolean insertMaintainerAvailability(MaintainerAvailability ava);
    public boolean updateMaintainerAvailability(MaintainerAvailability ava);
    public boolean deleteMaintainerAvailability(MaintainerAvailability ava);
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeek(int week, String username)throws SQLException;
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeekAndDay(int week,String username, int day);

}
