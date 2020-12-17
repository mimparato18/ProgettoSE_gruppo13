/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

import businesslayer.Procedure;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public interface MaintenanceProcedureDao {
    //CRUD
    /**
    * Insert a Procedure inside the database
    *
    * @param  procedure  the procedure to insert in the database
    * @return       true if successful, false otherwise
    */
    public boolean insertProcedure(Procedure procedure);
    /**
    * Update a Procedure inside the database
    *
    * @param  oldName  the name of procedure to update
    * @param  newName  the new name of the procedure
    * @return       true if successful, false otherwise
    */
    public boolean updateProcedure(String oldName, String newName);
    /**
    * Delete a Procedure from the database
    *
    * @param  name  name of procedure to delete
    * @return       true if successful, false otherwise
    */
    public boolean deleteProcedure(String name);
    /**
    * Get all procedures
    * @return       ArrayList of Procedure
    */
    public ArrayList<Procedure> getAllProcedures();
}
