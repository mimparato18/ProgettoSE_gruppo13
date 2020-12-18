/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.Site;
import dataaccesslayer.SiteDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class SiteDaoImplStub implements SiteDao {

    static ArrayList<Site> allSite;

    public SiteDaoImplStub() {
        allSite = new ArrayList<>();
    }

    private boolean isSite(Site site) {
        for (int i=0;i<allSite.size();i++) {
            //if (siteOccurrence.getBranchOffice().equals(site.getBranchOffice()) && siteOccurrence.getDepartment().equals(site.getDepartment())) {
            if (allSite.get(i).getBranchOffice().equals(site.getBranchOffice())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertSite(Site site) {
        if (!this.isSite(site)) {
            return allSite.add(site);
        }
        return false;
    }

    @Override
    public boolean updateSite(Site oldSite, String department) {
        if (this.isSite(oldSite)) {
            for (var siteOccurrence : allSite) {
                if (siteOccurrence == oldSite) {
                    int indexOld = allSite.indexOf(siteOccurrence);
                    siteOccurrence.setDepartment(department);
                    allSite.add(indexOld, siteOccurrence);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteSite(Site site) {
        //if (isSite(site)) {
            return allSite.remove(site);
        //}
        //return false;
    }

    @Override
    public ArrayList<Site> getAllSites() {
        return allSite;
    }

}
