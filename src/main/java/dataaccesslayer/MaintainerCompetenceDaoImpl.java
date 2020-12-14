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

/**
 *
 * @author Marco Calabrese
 */
public class MaintainerCompetenceDaoImpl implements MaintainerCompetenceDao {

    private ConnectionPool pool;

    public MaintainerCompetenceDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    
    public boolean isMaintainerCompetence(String username, String competence) {
        String selectQuery = String.format("SELECT username FROM maintainercompetence WHERE (username = '%s', competence = '%s')", username, competence);
        String usernameFetched = null;
        try (Connection connection = pool.getConnection(); Statement statement= connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)){
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
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateMaintainerCompetence(String username, String oldCompetence, String newCompetence) {
        if (this.isMaintainerCompetence(username, oldCompetence)) {
            return false;
        }
        String updateQuery = String.format("UPDATE maintainercompetence SET name = '%s' WHERE name = '%s' AND competence='%s'", newCompetence, username, oldCompetence);
        try (Connection connection = pool.getConnection();Statement statement = connection.createStatement()){
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteMaintainerCompetence(String username, String competence) {
        if (this.isMaintainerCompetence(username, competence)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM maintainercompetence WHERE name = '%s' AND competence='%s'", username, competence);
        try (Connection connection = pool.getConnection();Statement statement = connection.createStatement()){
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}