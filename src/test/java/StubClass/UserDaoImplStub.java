/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.Maintainer;
import businesslayer.User;
import dataaccesslayer.UserDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class UserDaoImplStub implements UserDao {

    static ArrayList<User> allUser;
    static ArrayList<String> allMaintainerCompetence;

    public UserDaoImplStub() {
        allUser = new ArrayList<>();
        allMaintainerCompetence = new ArrayList<>();
    }

    public boolean isUser(String username) {
        for (int i=0;i<allUser.size();i++) {
            if (allUser.get(i).getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertUser(User user, String role) {
        int i;
        if (!this.isUser(user.getUsername())) {

            allUser.add(user);
            if ("Maintainer".equals(role)) {
                Maintainer maintainer = (Maintainer) user;
                for (i = 0; i < maintainer.getCompetencies().size(); i++) {
                    allMaintainerCompetence.add(maintainer.getUsername() + "-" + maintainer.getCompetencies().get(i));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {

        if (this.isUser(user.getUsername())) {
            for (var userOccurrence : allUser) {
                if (userOccurrence.getUsername().equals(user.getUsername())) {
                    int indexOld = allUser.indexOf(userOccurrence);
                    allUser.add(indexOld, user);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        if (this.isUser(username)) {
            for (var userOccurrence : allUser) {
                if (userOccurrence.getUsername().equals(username)) {
                    return allUser.remove(userOccurrence);
                }
            }
        }
        return false;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        return allUser;
    }

    @Override
    public ArrayList<User> getAllMaintainers() {
        ArrayList<User> allMaintainers = new ArrayList<>();
        for (var userOccurrence : allUser) {
            if (userOccurrence instanceof Maintainer) {
                allMaintainers.add(userOccurrence);
            }
        }
        return allMaintainers;
    }

}
