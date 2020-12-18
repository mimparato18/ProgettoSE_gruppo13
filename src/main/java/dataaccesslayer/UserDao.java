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

    /**
     * Insert a user in the database
     *
     * @param user the user to insert in the database
     * @param role the role of the user
     * @return true if the insert was successful, false otherwise
     */
    public boolean insertUser(User user, String role);

    /**
     * Update a user in the database
     *
     * @param user the user to insert in the database
     * @return true if the update was successful, false otherwise
     */
    public boolean updateUser(User user);

    /**
     * Delete a user in the database
     *
     * @param username username of the user to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteUser(String username);

    /**
     * Get all users inside the database. The user with role "System
     * Administrator" is excluded
     *
     *
     * @return ArrayList of User
     */
    public ArrayList<User> getAllUsers();

    /**
     * Get all users with role "Maintainer" inside the database
     *
     *
     * @return ArrayList of User
     */
    public ArrayList<User> getAllMaintainers();
}
