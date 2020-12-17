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

    /**
     * Create a maintenance activity without an ID
     *
     * @param site site composed by branchoffice and department
     * @param typology typology of maintenance activity
     * @param description desciption of the maintenance activity
     * @param interventionTime hours estimated for the activity
     * @param interruptible describes if the activity can be interruptible
     * @param materials materials required by activity
     * @param week number of week for which the activity is planned
     * @param workspaceNotes notes for the activity
     * @return true if the update was successful, false otherwise
     */
    public MaintenanceActivity createActivity(Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return new MaintenanceActivity(site, typology, description, interventionTime,
                interruptible, materials, week, workspaceNotes);

    }

    /**
     * Create a maintenance activity with an ID
     *
     * @param id id of the activity
     * @param site site composed by branchoffice and department
     * @param typology typology of maintenance activity
     * @param description desciption of the maintenance activity
     * @param interventionTime hours estimated for the activity
     * @param interruptible describes if the activity can be interruptible
     * @param materials materials required by activity
     * @param week number of week for which the activity is planned
     * @param workspaceNotes notes for the activity
     * @return true if the update was successful, false otherwise
     */

    public MaintenanceActivity createActivity(int id, Site site, String typology, String description,
            int interventionTime, boolean interruptible, String materials, int week, String workspaceNotes) {

        return new MaintenanceActivity(id, site, typology, description, interventionTime,
                interruptible, materials, week, workspaceNotes);

    }

}
