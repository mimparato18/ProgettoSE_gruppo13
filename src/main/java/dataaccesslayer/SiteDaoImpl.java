/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Site;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class SiteDaoImpl implements SiteDao {

    private ConnectionPool pool;

    public SiteDaoImpl() {
        pool = ConnectionPool.getPool();
    }

    /**
     * Check if the site is inside the database
     *
     * @param site the site to check
     * @return true if the site exists, false otherwise
     */
    private boolean isSite(Site site) {
        String selectQuery = String.format("SELECT branchoffice, department FROM site WHERE (branchoffice = '%s' and department = '%s')", site.getBranchOffice(), site.getDepartment());
        String branchOffice = null;
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

            while (resultSet.next()) {
                branchOffice = resultSet.getString(1);
            }
            return (branchOffice != null);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean insertSite(Site site) {
        if (this.isSite(site)) {
            return false;
        }

        String insertQuery = String.format("INSERT INTO site(branchoffice,department) values ('%s','%s')", site.getBranchOffice(), site.getDepartment());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean updateSite(Site oldSite, String department) {
        if (!this.isSite(oldSite)) {
            return false;
        }
        String updateQuery = String.format("UPDATE site SET department = '%s' WHERE (branchoffice = '%s' and department = '%s')", department, oldSite.getBranchOffice(), oldSite.getDepartment());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public boolean deleteSite(Site site) {
        if (!this.isSite(site)) {
            return false;
        }
        String deleteQuery = String.format("DELETE FROM site WHERE (branchoffice = '%s' and department = '%s')", site.getBranchOffice(), site.getDepartment());
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement()) {

            statement.executeUpdate(deleteQuery);

            return true;

        } catch (SQLException ex) {

            System.out.println(ex);
            return false;
        }

    }

    @Override
    public ArrayList<Site> getAllSites() {
        String branchOffice;
        String department;
        ArrayList<Site> sitesList = new ArrayList<>();
        String selectQuery = String.format("SELECT * FROM site");
        try ( Connection connection = pool.getConnection();  Statement statement = connection.createStatement();  ResultSet resultSet = statement.executeQuery(selectQuery)) {

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

}
