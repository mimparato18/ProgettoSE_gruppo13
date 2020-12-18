/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import java.util.ArrayList;

/**
 *
 * @author marco calabrese
 */
public class SystemAdministrator extends User {

    public SystemAdministrator(String username, String password) {
        super(username, password);
    }

    /**
     * Create a Planner
     *
     * @param username username of the planner
     * @param password password of the planner
     * @return the Planner
     */
    public Planner createPlanner(String username, String password) {
        return new Planner(username, password);
    }

    /**
     * Create a Maintainer without competencies
     *
     * @param username username of the maintainer
     * @param password password of the maintainer
     * @return the Planner
     */
    public Maintainer createMaintainer(String username, String password) {
        return new Maintainer(username, password);
    }

    /**
     * Create a Maintainer with competencies
     *
     * @param username username of the maintainer
     * @param password password of the maintainer
     * @param competencies competencies of maintainer
     * @return the Planner
     */
    public Maintainer createMaintainer(String username, String password, ArrayList<String> competencies) {
        return new Maintainer(username, password, competencies);
    }
}
