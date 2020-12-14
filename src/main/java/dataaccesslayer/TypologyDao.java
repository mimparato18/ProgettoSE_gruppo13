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
    public boolean insertTypology(String type);
    public boolean updateTypology(String oldType, String newType);
    public boolean deleteTypology(String type);
    public ArrayList<String> getAllTypologies();
}
