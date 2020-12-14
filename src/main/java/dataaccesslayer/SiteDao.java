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
    public boolean insertSite(Site site);
    public boolean updateSite(Site oldSite, String department);
    public boolean deleteSite(Site site);
    public ArrayList<Site> getAllSites();
}
