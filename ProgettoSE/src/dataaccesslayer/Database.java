/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

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

    public void disconnect() throws SQLException {
        connection.close();
    }
}
