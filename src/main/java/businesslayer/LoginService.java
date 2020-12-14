/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import dataaccesslayer.UserDaoImpl;

/**
 *
 * @author marku
 */
public class LoginService {

    private UserDaoImpl userDao;

    public LoginService() {
        userDao = new UserDaoImpl();
    }

    public String checkCredentials(String username, String password) {
        if (userDao.isUser(username)) {
            if (password.equals(userDao.getPassword(username))) {
                return userDao.getRole(username);
            }
        }
        return null;
    }

}
