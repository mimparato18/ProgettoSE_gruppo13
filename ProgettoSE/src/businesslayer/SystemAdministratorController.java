/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import java.util.LinkedList;

/**
 *
 * @author marku
 */
public class SystemAdministratorController {//passare db come parametro
    private Database db;
    private SystemAdministrator admin; 

    public SystemAdministratorController(Database db) {
        this.db = db;         
        admin = new SystemAdministrator("admin","admin");
    }

    
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
    
   
    public LinkedList<User> viewUsers(){
        return db.getAllUsers();
    }
    
}
