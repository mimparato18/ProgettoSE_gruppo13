/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

/**
 *
 * @author marku
 */
public class Planner extends User {

    public Planner(String username, String password) {
        super(username, password);
    }

    public MaintenanceActivity createActivity(Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return new MaintenanceActivity(site, typology, description, interventionTime,
                interruptible, materials, week, workspaceNotes);

    }

    public void assignActivity() {

    }
}
