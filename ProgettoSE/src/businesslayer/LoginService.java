/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.Login;

/**
 *
 * @author marku
 */
public class LoginService {

    private Login db;

    public LoginService() {
        db = new Login();
        db.connect();
    }
    
    public String checkCredentials(String username, String password){
        if (db.isUser(username))
                if (password == db.getPassword(username))
                        return db.getRole(username);
        return null;
    }


}
