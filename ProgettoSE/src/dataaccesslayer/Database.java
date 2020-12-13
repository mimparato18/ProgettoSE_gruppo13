/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Procedure;
import businesslayer.Site;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author simo5
 */
public abstract class Database {

    private static final String DB_URL = "jdbc:mysql://40.115.39.127:3306/progettosedb";
    private static final String DB_USER = "progettose";
    private static final String DB_PASSWD = "progettose";
    private Connection connection = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;

    public Database() {
    }

    public boolean connect() {

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWD);
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean disconnect() {
        try{
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
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
    
    public boolean isProcedure(Procedure procedure) {
        String selectQuery = String.format("SELECT name FROM maintenanceprocedure WHERE (name = '%s')", procedure.getName());
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
    
    public boolean isActivity(int id) {
        String selectQuery = String.format("SELECT id FROM maintenanceactivity WHERE (id = '%d')", id);
        int checkID = 0;
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                checkID = resultSet.getInt(1);
            }
            return (checkID != 0);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean isCompetence(String competence) {
        String selectQuery = String.format("SELECT competencename FROM competence WHERE (competencename = '%s')", competence);
        String competenceName = null;
        try {
            resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                competenceName = resultSet.getString(1);
            }
            return (competenceName != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
