/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Maintainer;
import businesslayer.Planner;
import businesslayer.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class UserDaoImpl implements UserDao {

    private ConnectionPool pool;

    public UserDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    public boolean isUser(String username) {
        String selectQuery;
        String name = null;

        selectQuery = String.format("SELECT username FROM user WHERE (username = '%s')", username);
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                name = resultSet.getString(1);
            }
            return name != null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertUser(User user, String role) {
        int i;
        if (this.isUser(user.getUsername())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO user(username, password, role) values ('%s','%s','%s')", user.getUsername(), user.getPassword(), role);

        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            if ("Maintainer".equals(role)) {
                Maintainer maintainer = (Maintainer) user;

                for (i = 0; i < maintainer.getCompetencies().size(); i++) {
                    insertQuery = String.format("INSERT INTO maintainercompetence(username,competence) values ('%s','%s')", maintainer.getUsername(), maintainer.getCompetencies().get(i));
                    statement.executeUpdate(insertQuery);
                }

            }

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        if (!this.isUser(user.getUsername())) {
            return false;
        }
        String updateQuery = String.format("UPDATE user SET password = '%s' WHERE (username = '%s')", user.getPassword(), user.getUsername());
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteUser(String username) {
        if (!this.isUser(username)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM user WHERE (username = '%s')", username);
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public ArrayList<User> getAllUsers() {
        String username;
        String password;
        String role;
        ArrayList<User> usersList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM user");
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                username = resultSet.getString(1);
                password = resultSet.getString(2);
                role = resultSet.getString(3);
                if ("Maintainer".equals(role)) {
                    usersList.add(new Maintainer(username, password));
                } else if ("Planner".equals(role)) {
                    usersList.add(new Planner(username, password));
                }
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public ArrayList<User> getAllMaintainers() {
        String username;
        String password;
        ArrayList<User> usersList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM user WHERE role = 'Maintainer'");
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                username = resultSet.getString(1);
                password = resultSet.getString(2);
                
                usersList.add(new Maintainer(username, password));
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public User getUser(String username) {
        if (!this.isUser(username)) {
            return null;
        }
        String password = null;
        String role = null;

        String selectQuery = String.format("SELECT * FROM user WHERE (username = '%s')", username);
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery);) {

            while (resultSet.next()) {
                username = resultSet.getString(1);
                password = resultSet.getString(2);
                role = resultSet.getString(3);
            }
            if ("Maintainer".equals(role)) {
                Maintainer user = new Maintainer(username, password);
                return user;
            } else if ("Planner".equals(role)) {
                Planner user = new Planner(username, password);
                return user;
            }

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return null;
    }

    public String getRole(String username) {
        if (!this.isUser(username)) {
            return null;
        }
        String role = null;
        String selectQuery = String.format("SELECT role FROM user WHERE (username = '%s')", username);
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                role = resultSet.getString(1);
            }
            return role;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public String getPassword(String username) {
        if (!this.isUser(username)) {
            return null;
        }
        String password = null;
        String selectQuery = String.format("SELECT password FROM user WHERE (username = '%s')", username);
        try (Connection connection = pool.getConnection(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                password = resultSet.getString(1);
            }
            return password;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
