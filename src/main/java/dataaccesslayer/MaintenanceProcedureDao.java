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
    public boolean insertProcedure(Procedure procedure);
    public boolean updateProcedure(String oldName, String newName);
    public boolean deleteProcedure(String name);
    public ArrayList<Procedure> getAllProcedures();
}
