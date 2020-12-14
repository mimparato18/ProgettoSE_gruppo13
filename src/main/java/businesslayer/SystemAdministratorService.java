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
    //Create Planner
    public boolean addUser(String username, String password, String role) {

        return userDao.insertUser(admin.createPlanner(username, password), role);

    }

    //Create Maintainer
    public boolean addUser(String username, String password, String role, ArrayList<String> competencies) {
        return userDao.insertUser(admin.createMaintainer(username, password, competencies), role);

    }

    public boolean updateUser(String username, String password, String role) {
        if (role == "Mantainer") {
            return userDao.updateUser(admin.createMaintainer(username, password));
        } else {
            return userDao.updateUser(admin.createPlanner(username, password));
        }

    }

    public boolean deleteUser(String username) {
        return userDao.deleteUser(username);
    }

    public ArrayList<User> viewUsers() {
        return userDao.getAllUsers();

    }
    //Site management

    public boolean addSite(String branchOffice, String department) {
        return siteDao.insertSite(new Site(branchOffice, department));
    }

    public boolean updateSite(Site oldSite, String department) {
        return siteDao.updateSite(oldSite, department);
    }

    public boolean deleteSite(Site site) {
        return siteDao.deleteSite(site);
    }

    public ArrayList<Site> viewSites() {
        return siteDao.getAllSites();
    }

    //Typology management
    public boolean addTypology(String typology) {
        return typologyDao.insertTypology(typology);
    }

    public boolean updateTypology(String oldTypology, String newTypology) {
        return typologyDao.updateTypology(oldTypology, newTypology);
    }

    public boolean deleteTypology(String typology) {
        return typologyDao.deleteTypology(typology);
    }

    public ArrayList<String> viewTypologies() {
        return typologyDao.getAllTypologies();
    }

    //Procedure management
    public boolean addProcedure(String name, ArrayList<String> competencies) {
        return procedureDao.insertProcedure(new Procedure(name,competencies));
    }

    public boolean updateProcedure(String oldName, String newName) {
        return procedureDao.updateProcedure(oldName, newName);
    }

    public boolean deleteProcedure(String name) {
        return procedureDao.deleteProcedure(name);
    }

    public ArrayList<Procedure> viewProcedures() {
        return procedureDao.getAllProcedures();
    }

    //Competence management
    public boolean addCompetence(String competence) {
        return competenceDao.insertCompetence(competence);
    }

    public boolean updateCompetence(String oldCompetence, String newCompetence) {
        return competenceDao.updateCompetence(oldCompetence, newCompetence);
    }

    public boolean deleteCompetence(String competence) {
        return competenceDao.deleteCompetence(competence);
    }

    public ArrayList<String> viewCompetencies() {
        return competenceDao.getAllCompetencies();
    }
}
