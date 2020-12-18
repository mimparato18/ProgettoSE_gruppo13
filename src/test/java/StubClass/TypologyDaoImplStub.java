/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import dataaccesslayer.TypologyDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class TypologyDaoImplStub implements TypologyDao {

    static ArrayList<String> allTypology;

    public TypologyDaoImplStub() {
        allTypology = new ArrayList<>();
    }

    private boolean isTypology(String type) {
        return allTypology.contains(type);
    }

    @Override
    public boolean insertTypology(String type) {
        if (!this.isTypology(type)) {
            return allTypology.add(type);
        }
        return false;
    }

    @Override
    public boolean updateTypology(String oldType, String newType) {
        if (this.isTypology(oldType)) {
            for (var typologyOccurrence : allTypology) {
                if (typologyOccurrence.equals(oldType)) {
                    int indexOld = allTypology.indexOf(typologyOccurrence);
                    allTypology.add(indexOld, newType);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteTypology(String type) {
        return allTypology.remove(type);
    }

    @Override
    public ArrayList<String> getAllTypologies() {
        return allTypology;
    }

}
