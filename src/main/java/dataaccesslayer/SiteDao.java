/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Site;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public interface SiteDao {

    //CRUD
    /**
     * Insert a site in the database
     *
     * @param site the site to insert in the database
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertSite(Site site);

    /**
     * Update a site in the database
     *
     * @param oldSite the site to update in the database
     * @param department the department to replace the one in oldSite
     * @return true if the update was successful, false otherwise
     */
    public boolean updateSite(Site oldSite, String department);

    /**
     * Delete a site from the database
     *
     * @param site the site to delete from the database
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteSite(Site site);

    /**
     * Get all sites from the database
     *
     * @return ArrayList of Site
     */
    public ArrayList<Site> getAllSites();
}
