/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public interface MaintainerCompetenceDao {
    //CRUD
    /**
    * Insert a MaintainerCompetence inside the database
    *
    * @param  username  username of the maintainer
    * @param  competence the competence associated to the maintainer
    * @return       true if successful, false otherwise
    */
    public boolean insertMaintainerCompetence(String username,String competence);
    /**
    * Update a MaintainerCompetence inside the database
    *
    * @param  username  the username of the maintainer
    * @param  oldCompetence  the competence to update
    * @param  newCompetence  the new competence
    * @return       true if successful, false otherwise
    */
    public boolean updateMaintainerCompetence(String username,String oldCompetence,String newCompetence);
    /**
    * Delete a MaintainerCompetence inside the database
    *
    * @param  username  the username of the maintainer
    * @param  competence  the competence to be deleted
    * @return       true if successful, false otherwise
    */
    public boolean deleteMaintainerCompetence(String username,String competence);
    /**
    * Get all Maintainer Competencies associated to a Maintainer inside the database
    *
    * @param  username  the username of the maintainer
    * @return      ArrayList
    */
    public ArrayList<String> getMaintainerCompetence(String username);
}
