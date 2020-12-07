/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Maintainer;
import businesslayer.Planner;
import businesslayer.User;
import java.sql.SQLException;

/**
 *
 * @author simo5
 */
public class Login extends Database {

    public Login() {
    }

    public User getUser(String username) {
        if (!this.isUser(username)) {
            return null;
        }
        String password = null;
        String role = null;

        String selectQuery = String.format("SELECT * FROM user WHERE (username = '%s')", username);
        try {
            resultSet = statement.executeQuery(selectQuery);

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
        try {
            resultSet = statement.executeQuery(selectQuery);
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
        try {
            resultSet = statement.executeQuery(selectQuery);
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
