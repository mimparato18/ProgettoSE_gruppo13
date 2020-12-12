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

    private SystemAdministratorDataAccess db;
    private SystemAdministrator admin;

    public SystemAdministratorService(SystemAdministrator admin) {
        db = new SystemAdministratorDataAccess();
        db.connect();
        this.admin = admin;
    }

    //User Management
    //Create Planner
    public boolean addUser(String username, String password, String role) {

        return db.createUser(admin.createPlanner(username, password), role);

    }

    //Create Maintainer
    public boolean addUser(String username, String password, String role, String competencies) {
        ArrayList<String> list = new ArrayList<>();
        int len = competencies.split("\n").length;

        for (int i = 0; i < len; i++) {
            list.add(i, competencies.split("\n")[i]);
        }

        return db.createUser(admin.createMaintainer(username, password, list), role);

    }

    public boolean updateUser(String username, String password, String role) {
        if (role == "Mantainer") {
            return db.modifyUser(admin.createMaintainer(username, password));
        } else {
            return db.modifyUser(admin.createPlanner(username, password));
        }

    }

    public boolean deleteUser(String username) {
        return db.removeUser(username);
    }

    public ArrayList<User> viewUsers() {
        return db.getAllUsers();

    }
    //Site management

    public boolean addSite(String branchOffice, String department) {
        return db.createSite(new Site(branchOffice, department));
    }

    public boolean updateSite(Site oldSite, String department) {
        return db.modifySite(oldSite, department);
    }

    public boolean deleteSite(Site site) {
        return db.removeSite(site);
    }

    public ArrayList<Site> viewSites() {
        return db.getAllSites();
    }

    //Typology management
    public boolean addTypology(String typology) {
        return db.createTypology(typology);
    }

    public boolean updateTypology(String oldTypology, String newTypology) {
        return db.modifyTypology(oldTypology, newTypology);
    }

    public boolean deleteTypology(String typology) {
        return db.removeTypology(typology);
    }

    public ArrayList<String> viewTypologies() {
        return db.getAllTypologies();
    }

    //Procedure management
    public boolean addProcedure(String name) {
        return db.createProcedure(new Procedure(name));
    }

    public boolean updateProcedure(String oldName, String newName) {
        return db.modifyProcedure(oldName, newName);
    }

    public boolean deleteProcedure(String name) {
        return db.removeProcedure(name);
    }

    public ArrayList<Procedure> viewProcedures() {
        return db.getAllProcedures();
    }

    //Competence management
    public boolean addCompetence(String competence) {
        return db.createCompetence(competence);
    }

    public boolean updateCompetence(String oldCompetence, String newCompetence) {
        return db.modifyCompetence(oldCompetence, newCompetence);
    }

    public boolean deleteCompetence(String competence) {
        return db.removeCompetence(competence);
    }

    public ArrayList<String> viewCompetencies() {
        return db.getAllCompetencies();
    }
}
