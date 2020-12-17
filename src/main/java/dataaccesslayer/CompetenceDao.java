/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import java.util.ArrayList;

/**
 *
 * @author marku
 */
public interface CompetenceDao {

    //CRUD
    /**
     * Insert a competence inside the database
     *
     * @param competence the competence to insert in the database
     * @return true if successful, false otherwise
     */
    public boolean insertCompetence(String competence);

    /**
     * Update a competence inside the database
     *
     * @param oldCompetence the competence to update in the database
     * @param newCompetence the new competence
     * @return true if successful, false otherwise
     */
    public boolean updateCompetence(String oldCompetence, String newCompetence);

    /**
     * Delete a competence from the database
     *
     * @param competence the competence to delete from the database
     * @return true if successful, false otherwise
     */
    public boolean deleteCompetence(String competence);

    /**
     * Get all competencies from the database
     *
     * @return ArrayList
     */
    public ArrayList<String> getAllCompetencies();
}
