/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.SQLException;
import java.util.ArrayList;
import businesslayer.*;

/**
 *
 * @author simo5
 */
public class SystemAdministratorDataAccess extends Database {

    public SystemAdministratorDataAccess() {
    }

    /*------USER METHODS------*/
    public boolean isUser(String username) {
        String selectQuery;
        String name = null;

        selectQuery = String.format("SELECT username FROM user WHERE (username = '%s')", username);
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                name = resultSet.getString(1);
            }
            return name != null;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
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

    public boolean createUser(User user, String role) {
        int i;
        if (this.isUser(user.getUsername())) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO user(username, password, role) values ('%s','%s','%s')", user.getUsername(), user.getPassword(), role);

        try {
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

    public boolean modifyUser(User user) {
        if (!this.isUser(user.getUsername())) {
            return false;
        }
        String updateQuery = String.format("UPDATE user SET password = %s WHERE (username = '%s')", user.getPassword(), user.getUsername());
        try {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean removeUser(String username) {
        if (!this.isUser(username)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM user WHERE (username = '%s')", username);
        try {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<User> getAllUsers() {
        String username;
        String password;
        String role;
        ArrayList<User> usersList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM user");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                username = resultSet.getString(1);
                password = resultSet.getString(2);
                role = resultSet.getString(3);
                if ("Maintainer".equals(role)) {
                    usersList.add(new Maintainer(username, password));
                } else if ("Planner".equals(role)) {
                    usersList.add(new Maintainer(username, password));
                }
            }
            return usersList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    /*------SITE METHODS------*/
    public boolean isSite(Site site) {
        String selectQuery = String.format("SELECT branchoffice, department FROM site WHERE (branchoffice = '%s' and department = '%s')", site.getBranchOffice(), site.getDepartment());
        String branchOffice = null;
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                branchOffice = resultSet.getString(1);
            }
            return (branchOffice != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean createSite(Site site) {
        if (this.isSite(site)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO site(branchoffice,department) values ('%s','%s')", site.getBranchOffice(), site.getDepartment());
        try {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean modifySite(Site oldSite, String department) {
        if (!this.isSite(oldSite)) {
            return false;
        }
        String updateQuery = String.format("UPDATE site SET department = '%s' WHERE (branchoffice = '%s' and department = '%s')", department, oldSite.getBranchOffice(), oldSite.getDepartment());
        try {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean removeSite(Site site) {
        if (!this.isSite(site)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM site WHERE (branchoffice = '%s' and department = '%s')", site.getBranchOffice(), site.getDepartment());
        try {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<Site> getAllSites() {
        String branchOffice;
        String department;
        ArrayList<Site> sitesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM site");
        try {
            resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                branchOffice = resultSet.getString(1);
                department = resultSet.getString(2);
                sitesList.add(new Site(branchOffice, department));
            }
            return sitesList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    /*------TYPOLOGY METHODS------*/
    public boolean isTypology(String type) {
        String selectQuery = String.format("SELECT type FROM typology WHERE (type = '%s')", type);
        String typology = null;
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                typology = resultSet.getString(1);
            }
            return (typology != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean createTypology(String type) {
        if (this.isTypology(type)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO typology(type) values ('%s')", type);
        try {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean modifyTypology(String oldType, String newType) {
        if (!this.isTypology(oldType)) {
            return false;
        }
        String updateQuery = String.format("UPDATE typology SET type = '%s' WHERE type = '%s'", newType, oldType);
        try {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean removeTypology(String type) {
        if (!this.isTypology(type)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM typology WHERE type = '%s'", type);
        try {
            statement.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public ArrayList<String> getAllTypologies() {
        String type;
        ArrayList<String> typologiesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM typology");
        try {
            resultSet = statement.executeQuery(selectQuery);
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
