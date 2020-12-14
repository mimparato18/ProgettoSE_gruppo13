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

/**
 *
 * @author marku
 */
public class CompetenceDaoImpl implements CompetenceDao {

    private ConnectionPool pool;

    public CompetenceDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    private boolean isCompetence(String competence) {
        String selectQuery = String.format("SELECT competencename FROM competence WHERE (competencename = '%s')", competence);
        String competenceName = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                competenceName = resultSet.getString(1);
            }
            return (competenceName != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertCompetence(String competence) {
        if (this.isCompetence(competence)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO competence(competencename) values ('%s')", competence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateCompetence(String oldCompetence, String newCompetence) {

        if (!this.isCompetence(oldCompetence)) {
            return false;
        }
        String updateQuery = String.format("UPDATE competence SET competencename = '%s' WHERE competencename = '%s'", newCompetence, oldCompetence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteCompetence(String competence) {
        if (!this.isCompetence(competence)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM competence WHERE competencename = '%s'", competence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<String> getAllCompetencies() {
        String competence;
        ArrayList<String> competenciesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM competence");
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                competence = resultSet.getString(1);
                competenciesList.add(competence);
            }
            return competenciesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
