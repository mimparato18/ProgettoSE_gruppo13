/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author marku
 *
 * Singleton to have a single connection pool in all the application.
 */
public class ConnectionPool {

    private static BasicDataSource dataSource = null;
    private static ConnectionPool pool = null;

    static {
        dataSource = new BasicDataSource();
        //credentials to access the database
        dataSource.setUrl("jdbc:mysql://40.115.39.127:3306/progettosedb");
        dataSource.setUsername("progettose");
        dataSource.setPassword("progettose");
        //parameters of connection pool
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxTotal(25);

    }

    private ConnectionPool() {

    }

    public static ConnectionPool getPool() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * Get a connection from the connection pool
     *
     * @return java.sql.Connection
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }

}
