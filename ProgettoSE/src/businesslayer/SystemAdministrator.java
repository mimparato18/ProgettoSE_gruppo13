/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

/**
 *
 * @author marco calabrese
 */
public class SystemAdministrator extends User{

    public SystemAdministrator(String username, String password) {
        super(username, password);
    }
    
    public Planner createPlanner(){
        return null;
    }
    
    public Maintainer createMaintainer(){
        return null;
    }
}
