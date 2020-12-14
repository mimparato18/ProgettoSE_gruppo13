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

    public Planner createPlanner(String username, String password) {
        return new Planner(username, password);
    }

    public Maintainer createMaintainer(String username, String password) {
        return new Maintainer(username, password);
    }

    public Maintainer createMaintainer(String username, String password, ArrayList<String> competencies) {
        return new Maintainer(username, password, competencies);
    }
}
