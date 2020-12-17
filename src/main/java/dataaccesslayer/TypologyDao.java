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
public interface TypologyDao {
    //CRUD
    /**
    * Insert a Typology in the database
    *
    * @param  type  the typology to insert in the database
    * @return       true if the insert was successful, false otherwise   
    */
    public boolean insertTypology(String type);
    /**
    * Update a Typology in the database
    *
    * @param  oldType  the typology to update in the database
    * @param  newType  the typology after the update
    * @return       true if the update was successful, false otherwise   
    */
    public boolean updateTypology(String oldType, String newType);
    /**
    * Delete a Typology from the database
    *
    * @param  type  the typology to delete from the database
    * @return       true if the deletion was successful, false otherwise   
    */
    public boolean deleteTypology(String type);
    /**
    * Get all typologies
    *
    * @return     ArrayList of String 
    */
    public ArrayList<String> getAllTypologies();
}
