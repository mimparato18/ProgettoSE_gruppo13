/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.MaintainerAvailability;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author simo5
 */
public class MaintainerAvailabilityDaoImpl implements MaintainerAvailabilityDao{
    
    private ConnectionPool pool;

    public MaintainerAvailabilityDaoImpl() {
        pool = ConnectionPool.getPool();
    }
    
    private boolean isAvailableByWeek(String username, int week) {
        String selectQuery = String.format("SELECT username FROM maintaineravailability WHERE (username = '%s' and week = %d)", username, week);
        String user = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                user = resultSet.getString(1);
            }
            return (user != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    private boolean isAvailableByDay(String username, int week, int day) {
        String selectQuery = String.format("SELECT username FROM maintaineravailability WHERE (username = '%s' and week = %d and day = %d )", username, week, day);
        String user = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                user = resultSet.getString(1);
            }
            return (user != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertMaintainerAvailability(MaintainerAvailability ava) {
        if (this.isAvailableByDay(ava.getMaintainer(),ava.getWeek(), ava.getDay())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO maintaineravailability(username, week, day, hour1, hour2, hour3, hour4, hour5, hour6, hour7) "
                + "values ('%s', %d, %d, %d, %d, %d, %d, %d, %d, %d)", ava.getMaintainer(), ava.getWeek(), ava.getDay(), 
                ava.getHours()[0], ava.getHours()[1], ava.getHours()[2], ava.getHours()[3], ava.getHours()[4], ava.getHours()[5], ava.getHours()[6]);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateMaintainerAvailability(MaintainerAvailability ava) {
        if (!this.isAvailableByDay(ava.getMaintainer(), ava.getWeek(), ava.getDay())) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintaineravailability SET hour1 = %d, hour2 = %d, hour3 = %d, hour4 = %d, hour5 = %d, hour6 = %d, hour7 = %d"
                + "WHERE username = '%s' and week = %d and day = %d", ava.getHours()[0], ava.getHours()[1], ava.getHours()[2], ava.getHours()[3], ava.getHours()[4], ava.getHours()[5], ava.getHours()[6],
                ava.getMaintainer(), ava.getWeek(), ava.getDay());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteMaintainerAvailability(MaintainerAvailability ava) {
        if (!this.isAvailableByDay(ava.getMaintainer(), ava.getWeek(), ava.getDay())) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintaineravailability WHERE username = '%s' and week = %d and day = %d", ava.getMaintainer(), ava.getWeek(), ava.getDay());
        try ( Connection connection = pool.getConnection()) {
            try ( Statement statement = connection.createStatement()) {
                statement.executeUpdate(deleteQuery);
                return true;
            } catch (SQLException ex) {
                return false;
            }
        } catch (SQLException ex) {

            System.out.println(ex);
            return false;
        }
    }

    @Override
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeekAndDay(int week, String username, int day) {
       if (!this.isAvailableByDay(username,week,day)) {
            return null;
        }
        int i, k;
        int[] hours = new int[7];
        
        ArrayList<MaintainerAvailability> avaList = new ArrayList<>();
        
        String selectQuery = String.format("SELECT * FROM maintaineravailability where username = '%s' and week = %d and day = %d", username, week, day);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                username = resultSet.getString(1);
                week = resultSet.getInt(2);
                day = resultSet.getInt(3);
                for (i=0,k=4;i<7;i++,k++){
                    hours[i] = resultSet.getInt(k);
                }
                MaintainerAvailability ava = new MaintainerAvailability(username,week,day,hours);
                avaList.add(ava);
            }
            return avaList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<MaintainerAvailability> getMaintainerAvailabilitiesByWeek(int week, String username) throws SQLException {
       if (!this.isAvailableByWeek(username,week)) {
            return null;
        }
        int day, i, k;
        int[] hours = new int[7];
        
        ArrayList<MaintainerAvailability> avaList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM maintaineravailability where username = '%s' and week = %d", username, week);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {
            
            while (resultSet.next()) {
                
                username = resultSet.getString(1);
                week = resultSet.getInt(2);
                day = resultSet.getInt(3);
                for (i=0,k=4;i<7;i++,k++){
                    hours[i] = resultSet.getInt(k);
                }
                MaintainerAvailability ava = new MaintainerAvailability(username,week,day,hours);
                avaList.add(ava);
            }
            return avaList;
        } 
    }
}
