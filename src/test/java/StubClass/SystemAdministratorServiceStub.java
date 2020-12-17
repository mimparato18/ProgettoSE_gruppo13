/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.Procedure;
import businesslayer.Site;
import businesslayer.SystemAdministrator;
import businesslayer.User;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class SystemAdministratorServiceStub {

    private SystemAdministrator admin;
    private UserDaoImplStub userDaoStub;
    private SiteDaoImplStub siteDaoStub;
    private TypologyDaoImplStub typologyDaoStub;
    private CompetenceDaoImplStub competenceDaoStub;
    private MaintenanceProcedureDaoImplStub procedureDaoStub;

    public SystemAdministratorServiceStub(SystemAdministrator admin) {
        this.admin = admin;
        this.userDaoStub = new UserDaoImplStub();
        this.siteDaoStub = new SiteDaoImplStub();
        this.typologyDaoStub = new TypologyDaoImplStub();
        this.competenceDaoStub = new CompetenceDaoImplStub();
        this.procedureDaoStub = new MaintenanceProcedureDaoImplStub();
    }

    //User Management
    //Create Planner
    public boolean addUser(String username, String password, String role) {

        return userDaoStub.insertUser(admin.createPlanner(username, password), role);

    }

    //Create Maintainer
    public boolean addUser(String username, String password, String role, ArrayList<String> competencies) {
        return userDaoStub.insertUser(admin.createMaintainer(username, password, competencies), role);

    }

    public boolean updateUser(String username, String password, String role) {
        if ("Mantainer".equals(role)) {
            return userDaoStub.updateUser(admin.createMaintainer(username, password));
        } else {
            return userDaoStub.updateUser(admin.createPlanner(username, password));
        }

    }

    public boolean deleteUser(String username) {
        return userDaoStub.deleteUser(username);
    }

    public ArrayList<User> viewUsers() {
        return userDaoStub.getAllUsers();

    }

    //Site management
    public boolean addSite(String branchOffice, String department) {
        return siteDaoStub.insertSite(new Site(branchOffice, department));
    }

    public boolean updateSite(Site oldSite, String department) {
        return siteDaoStub.updateSite(oldSite, department);
    }

    public boolean deleteSite(Site site) {
        return siteDaoStub.deleteSite(site);
    }

    public ArrayList<Site> viewSites() {
        return siteDaoStub.getAllSites();
    }

    //Typology management
    public boolean addTypology(String typology) {
        return typologyDaoStub.insertTypology(typology);
    }

    public boolean updateTypology(String oldTypology, String newTypology) {
        return typologyDaoStub.updateTypology(oldTypology, newTypology);
    }

    public boolean deleteTypology(String typology) {
        return typologyDaoStub.deleteTypology(typology);
    }

    public ArrayList<String> viewTypologies() {
        return typologyDaoStub.getAllTypologies();
    }

    //Procedure management
    public boolean addProcedure(String name, ArrayList<String> competencies) {
        return procedureDaoStub.insertProcedure(new Procedure(name, competencies));
    }

    public boolean updateProcedure(String oldName, String newName) {
        return procedureDaoStub.updateProcedure(oldName, newName);
    }

    public boolean deleteProcedure(String name) {
        return procedureDaoStub.deleteProcedure(name);
    }

    public ArrayList<Procedure> viewProcedures() {
        return procedureDaoStub.getAllProcedures();
    }

    //Competence management
    public boolean addCompetence(String competence) {
        return competenceDaoStub.insertCompetence(competence);
    }

    public boolean updateCompetence(String oldCompetence, String newCompetence) {
        return competenceDaoStub.updateCompetence(oldCompetence, newCompetence);
    }

    public boolean deleteCompetence(String competence) {
        return competenceDaoStub.deleteCompetence(competence);
    }

    public ArrayList<String> viewCompetencies() {
        return competenceDaoStub.getAllCompetencies();
    }
}
