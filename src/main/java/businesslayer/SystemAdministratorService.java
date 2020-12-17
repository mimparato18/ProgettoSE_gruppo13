/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.*;

import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class SystemAdministratorService {

    private SystemAdministrator admin;
    private UserDaoImpl userDao;
    private SiteDaoImpl siteDao;
    private TypologyDaoImpl typologyDao;
    private CompetenceDaoImpl competenceDao;
    private MaintenanceProcedureDaoImpl procedureDao;

    public SystemAdministratorService(SystemAdministrator admin) {
        userDao = new UserDaoImpl();
        siteDao = new SiteDaoImpl();
        typologyDao = new TypologyDaoImpl();
        competenceDao = new CompetenceDaoImpl();
        procedureDao = new MaintenanceProcedureDaoImpl();
        this.admin = admin;
    }

    //User Management
    /**
     * Add a user to the database by calling the corresponding DAO. Used to
     * create a planner
     *
     * @param username username of the new user
     * @param password password of the new user
     * @param role role of the new user
     * @return true if the insert was successful, false otherwise
     */
    public boolean addUser(String username, String password, String role) {

        return userDao.insertUser(admin.createPlanner(username, password), role);

    }

    /**
     * Add a user to the database by calling the corresponding DAO. Overload of
     * the method to create a maintainer
     *
     * @param username username of the new user
     * @param password password of the new user
     * @param role role of the new user
     * @param competencies competencies of the new user
     * @return true if the insert was successful, false otherwise
     */
    public boolean addUser(String username, String password, String role, ArrayList<String> competencies) {
        return userDao.insertUser(admin.createMaintainer(username, password, competencies), role);

    }

    /**
     * Update the password of the selected user
     *
     * @param username username of the user
     * @param password new password of user
     * @param role role of the user
     * @return true if the update was successful, false otherwise
     */
    public boolean updateUser(String username, String password, String role) {
        if (role == "Mantainer") {
            return userDao.updateUser(admin.createMaintainer(username, password));
        } else {
            return userDao.updateUser(admin.createPlanner(username, password));
        }

    }

    /**
     * Delete the user
     *
     * @param username username of the user
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    /**
     * Get all the users
     *
     *
     * @return ArrayList of User
     */
    public ArrayList<User> viewUsers() {
        return userDao.getAllUsers();

    }

    //Site management
    /**
     * Add a site using the corresponding dao
     *
     * @param branchOffice branch office of the new site
     * @param department department of the new site
     * @return true if the insert was successful, false otherwise
     */
    public boolean addSite(String branchOffice, String department) {
        return siteDao.insertSite(new Site(branchOffice, department));
    }

    /**
     * Update a site using the corresponding dao
     *
     * @param oldSite site that needs to be updated
     * @param department department to update
     * @return true if the update was successful, false otherwise
     */
    public boolean updateSite(Site oldSite, String department) {
        return siteDao.updateSite(oldSite, department);
    }

    /**
     * Delete a site using the corresponding dao
     *
     * @param site to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteSite(Site site) {
        return siteDao.deleteSite(site);
    }

    /**
     * Get all sites using the corresponding dao
     *
     * @return ArrayList of Site
     */
    public ArrayList<Site> viewSites() {
        return siteDao.getAllSites();
    }

    //Typology management
    /**
     * Add a typology using the corresponding dao
     *
     * @param typology typology to add
     * @return true if the insert was successful, false otherwise
     */
    public boolean addTypology(String typology) {
        return typologyDao.insertTypology(typology);
    }

    /**
     * Update a typology using the corresponding dao
     *
     * @param oldTypology typology to update
     * @param newTypology typology after the update
     * @return true if the insert was successful, false otherwise
     */
    public boolean updateTypology(String oldTypology, String newTypology) {
        return typologyDao.updateTypology(oldTypology, newTypology);
    }

    /**
     * Delete a typology using the corresponding dao
     *
     * @param typology to delete
     * @return true if the insert was successful, false otherwise
     */
    public boolean deleteTypology(String typology) {
        return typologyDao.deleteTypology(typology);
    }

    /**
     * Get all typologies
     *
     *
     * @return ArrayList of string
     */
    public ArrayList<String> viewTypologies() {
        return typologyDao.getAllTypologies();
    }

    //Procedure management
    /**
     * Add a Procedure using the corresponding dao
     *
     * @param name name of procedure
     * @param competencies competencies associated to the procedure
     * @return true if the insert was successful, false otherwise
     */
    public boolean addProcedure(String name, ArrayList<String> competencies) {
        return procedureDao.insertProcedure(new Procedure(name, competencies));
    }

    /**
     * Update a Procedure using the corresponding dao
     *
     * @param oldName name of procedure to update
     * @param newName new name of procedure
     * @return true if the update was successful, false otherwise
     */
    public boolean updateProcedure(String oldName, String newName) {
        return procedureDao.updateProcedure(oldName, newName);
    }

    /**
     * Delete a Procedure using the corresponding dao
     *
     * @param name
     * @return true if the delete was successful, false otherwise
     */
    public boolean deleteProcedure(String name) {
        return procedureDao.deleteProcedure(name);
    }

    /**
     * Get all procedures using the corresponding dao
     *
     * @return ArrayList of Procedure
     */
    public ArrayList<Procedure> viewProcedures() {
        return procedureDao.getAllProcedures();
    }

    //Competence management
    /**
     * Add a competence using the corresponding dao
     *
     * @param competence competence to add
     * @return true if the insert was successful, false otherwise
     */
    public boolean addCompetence(String competence) {
        return competenceDao.insertCompetence(competence);
    }

    /**
     * Update a competence using the corresponding dao
     *
     * @param oldCompetence competence to update
     * @param newCompetence new competence
     * @return true if the update was successful, false otherwise
     */
    public boolean updateCompetence(String oldCompetence, String newCompetence) {
        return competenceDao.updateCompetence(oldCompetence, newCompetence);
    }

    /**
     * Delete a competence using the corresponding dao
     *
     * @param competence competence to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteCompetence(String competence) {
        return competenceDao.deleteCompetence(competence);
    }

    /**
     * Get all competences using the corresponding dao
     *
     * @return ArrayList of string
     */
    public ArrayList<String> viewCompetencies() {
        return competenceDao.getAllCompetencies();
    }
}
