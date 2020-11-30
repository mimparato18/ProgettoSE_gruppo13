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
public class SystemAdministratorController {
    private SystemAdministratorDataAccess db;
    private SystemAdministrator admin; 

    public SystemAdministratorController() {
        this.db = db;         
        db.Connect();
        admin = new SystemAdministrator("admin","admin");
    }

    //User Management
    public boolean addUser(String username, String password, String role){
        
        if(role=="Mantainer")
            return db.createUser(admin.createMaintainer(username,password), role);
        else
            return db.createUser(admin.createPlanner(username,password), role);
        
        
    }
    
     public boolean updateUser(String username, String password, String role){
        if(role=="Mantainer")
            return db.modifyUser(admin.createMaintainer(username,password));
        else
            return db.modifyUser(admin.createPlanner(username,password));
        
    }
    
    public boolean deleteUser(String username){
        return db.removeUser(username);
    }
    
   
    public ArrayList<User> viewUsers(){
        return db.getAllUsers(); 
        
    }
    //Site management
    
    public boolean addSite(String branchOffice, String department){
        return db.createSite(new Site(branchOffice,department));
    }
    
    public boolean updateSite(Site oldSite, String department){
        return db.modifySite(oldSite, department);
    }
    
    public boolean deleteSite(Site site){
        return db.removeSite(site);
    }
    
    public ArrayList<Site> viewSites(){
        return db.getAllSites();
    }
    
    //Typology management
    
    public boolean addTypology(String typology){
        return db.createTypology(typology);
    }
    
    public boolean updateTypology(String oldTypology, String newTypology){
        return db.modifySite(oldTypology, newTypology);
    }
    
    public boolean deleteTypology(String typology){
        return db.removeSite(typology);
    }
    
    public ArrayList<String> viewTypologies(){
        return db.getAllTypologies();
    }

    
}
