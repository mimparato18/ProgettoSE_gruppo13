/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.User;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public interface UserDao {
    //CRUD
    public boolean insertUser(User user, String role);
    public boolean updateUser(User user);
    public boolean deleteUser(String username);
    public ArrayList<User> getAllUsers();
}
