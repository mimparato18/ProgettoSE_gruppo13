/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Marco Calabrese
 */
public class TypologyDaoImpl implements TypologyDao {

    private ConnectionPool pool;

    public TypologyDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    private boolean isTypology(String type) {
        String selectQuery = String.format("SELECT type FROM typology WHERE (type = '%s')", type);
        String typology = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                typology = resultSet.getString(1);
            }
            return (typology != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertTypology(String type) {
        if (this.isTypology(type)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO typology(type) values ('%s')", type);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateTypology(String oldType, String newType) {
        if (!this.isTypology(oldType)) {
            return false;
        }
        String updateQuery = String.format("UPDATE typology SET type = '%s' WHERE type = '%s'", newType, oldType);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteTypology(String type) {
        if (!this.isTypology(type)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM typology WHERE type = '%s'", type);
        try ( Connection connection = pool.getConnection()) {
            try ( Statement statement = connection.createStatement()) {
                connection.setAutoCommit(false);
                statement.execute("SET FOREIGN_KEY_CHECKS = 0");
                TimeUnit.MILLISECONDS.sleep(200);
                statement.executeUpdate(deleteQuery);
                TimeUnit.MILLISECONDS.sleep(200);
                statement.execute("SET FOREIGN_KEY_CHECKS = 1");
                connection.commit();
                return true;
            } catch (SQLException ex) {
                connection.rollback();
                return false;
            }
        } catch (SQLException | InterruptedException ex) {

            System.out.println(ex);
            return false;
        }
    }

    @Override
    public ArrayList<String> getAllTypologies() {
        String type;
        ArrayList<String> typologiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM typology");
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

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

}
