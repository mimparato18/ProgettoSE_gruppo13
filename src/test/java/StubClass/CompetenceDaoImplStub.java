/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import dataaccesslayer.CompetenceDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class CompetenceDaoImplStub implements CompetenceDao {

    static ArrayList<String> allCompetence;

    public CompetenceDaoImplStub() {
        allCompetence = new ArrayList<>();
    }

    private boolean isCompetence(String competence) {
        for (var competenceOccurrence : allCompetence) {
            if (competenceOccurrence.equals(competence)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertCompetence(String competence) {
        if (!this.isCompetence(competence)) {
            return allCompetence.add(competence);
        }
        return false;
    }

    @Override
    public boolean updateCompetence(String oldCompetence, String newCompetence) {
        if (this.isCompetence(oldCompetence)) {
            for (var competenceOccurrence : allCompetence) {
                if (competenceOccurrence.equals(oldCompetence)) {
                    int indexOld = allCompetence.indexOf(competenceOccurrence);
                    allCompetence.add(indexOld, newCompetence);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteCompetence(String competence) {
        if (isCompetence(competence)) {
            System.out.println("!!");
            return allCompetence.remove(competence);
        }
        return false;
    }

    @Override
    public ArrayList<String> getAllCompetencies() {
        return allCompetence;
    }

}
