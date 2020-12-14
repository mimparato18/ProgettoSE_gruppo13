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
    public boolean insertCompetence(String competence);
    public boolean updateCompetence(String oldCompetence, String newCompetence);
    public boolean deleteCompetence(String competence);
    public ArrayList<String> getAllCompetencies();
}
