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
 * @author Marco Calabrese
 */
public class MaintainerCompetenceDaoImpl implements MaintainerCompetenceDao {

    private ConnectionPool pool;

    public MaintainerCompetenceDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    /**
     * Check if the MaintainerCompetence exists in the database
     *
     * @param username the username of the maintainer
     * @param competence the competence associated to a maintainer
     * @return return true if the MaintainerCompetence exists, false otherwise
     */
    private boolean isMaintainerCompetence(String username, String competence) {
        String selectQuery = String.format("SELECT username FROM maintainercompetence WHERE (username = '%s', competence = '%s')", username, competence);
        String usernameFetched = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {
            while (resultSet.next()) {
                usernameFetched = resultSet.getString(1);
            }
            return (usernameFetched != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertMaintainerCompetence(String username, String competence) {
        if (this.isMaintainerCompetence(username, competence)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO maintainercompetence(username, competence) values ('%s','%s')", username, competence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateMaintainerCompetence(String username, String oldCompetence, String newCompetence) {
        if (!this.isMaintainerCompetence(username, oldCompetence)) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintainercompetence SET competence = '%s' WHERE username = '%s' AND competence='%s'", newCompetence, username, oldCompetence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteMaintainerCompetence(String username, String competence) {
        if (!this.isMaintainerCompetence(username, competence)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintainercompetence WHERE username = '%s' AND competence='%s'", username, competence);
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<String> getMaintainerCompetence(String username) {

        ArrayList<String> competencies = new ArrayList<>();
        String selectQuery = String.format("SELECT competence FROM maintainercompetence WHERE username = '%s'", username);
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
