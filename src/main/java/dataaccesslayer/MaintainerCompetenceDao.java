/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccesslayer;

/**
 *
 * @author Marco Calabrese
 */
public interface MaintainerCompetenceDao {
    //CRUD
    public boolean insertMaintainerCompetence(String username,String competence);
    public boolean updateMaintainerCompetence(String username,String oldCompetence,String newCompetence);
    public boolean deleteMaintainerCompetence(String username,String competence);
    //public ArrayList<User> getMaintainerCompetence();
}
