/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Procedure;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class MaintenanceProcedureDaoImpl implements MaintenanceProcedureDao {

    private ConnectionPool pool;

    public MaintenanceProcedureDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    public boolean isProcedure(String name) {
        String selectQuery = String.format("SELECT name FROM maintenanceprocedure WHERE (name = '%s')", name);
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
    public boolean insertProcedure(Procedure procedure) {
        if (this.isProcedure(procedure.getName())) {
            return false;
        }
        int i;
        String insertQuery = "";
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();) {
            for (i = 0; i < procedure.getCompetencies().size(); i++) {
                insertQuery = String.format("INSERT INTO maintenanceprocedure(name,competencename) values ('%s','%s')", procedure.getName(), procedure.getCompetencies().get(i));
                statement.executeUpdate(insertQuery);
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateProcedure(String oldName, String newName) {
        if (!this.isProcedure(oldName)) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintenanceprocedure SET name = '%s' WHERE name = '%s'", newName, oldName);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteProcedure(String name) {
        if (!this.isProcedure(name)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintenanceprocedure WHERE name = '%s'", name);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();) {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<Procedure> getAllProcedures() {
        String name;
        ArrayList<String> competencies;
        ArrayList<Procedure> proceduresList = new ArrayList<>();
        String selectQuery = String.format("SELECT name FROM maintenanceprocedure GROUP BY name");
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {

                name = resultSet.getString(1);
                competencies = this.getCompetenciesByName(name);

                proceduresList.add(new Procedure(name, competencies));

            }
            return proceduresList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public ArrayList<String> getCompetenciesByName(String name) {

        ArrayList<String> competencies = new ArrayList<>();
        String selectQuery = String.format("SELECT competencename FROM maintenanceprocedure WHERE name = '%s'", name);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                competencies.add(resultSet.getString(1));
            }
            return competencies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

    }
}
