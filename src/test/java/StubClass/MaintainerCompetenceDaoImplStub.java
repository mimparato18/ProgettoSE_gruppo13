/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import dataaccesslayer.MaintainerCompetenceDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class MaintainerCompetenceDaoImplStub implements MaintainerCompetenceDao {

    static ArrayList<String> allMaintainerCompetence;//name-competence_i-esima

    public MaintainerCompetenceDaoImplStub() {
        allMaintainerCompetence = new ArrayList<>();
    }

    public boolean isMaintainerCompetence(String username, String competence) {

        for (int i = 0; i < allMaintainerCompetence.size(); i++) {
            String string = allMaintainerCompetence.get(i);
            String[] parts = string.split("-");
            if ((username == parts[0]) && (competence == parts[1])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertMaintainerCompetence(String username, String competence) {

        if (!this.isMaintainerCompetence(username, competence)) {
            allMaintainerCompetence.add(username + "-" + competence);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMaintainerCompetence(String username, String oldCompetence, String newCompetence) {
        if (this.isMaintainerCompetence(username, oldCompetence)) {
            for (int i = 0; i < allMaintainerCompetence.size(); i++) {
                String string = allMaintainerCompetence.get(i);
                String[] parts = string.split("-");
                if ((username == parts[0]) && (oldCompetence == parts[1])) {
                    allMaintainerCompetence.add(i, username + "-" + newCompetence);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMaintainerCompetence(String username, String competence) {
        if (this.isMaintainerCompetence(username, competence)) {
            return allMaintainerCompetence.remove(username + "-" + competence);
        }
        return false;
    }

    @Override
    public ArrayList<String> getMaintainerCompetence(String username) {
        ArrayList<String> competencies = allMaintainerCompetence;

        for (int i = 0; i < allMaintainerCompetence.size(); i++) {
            if (competencies.get(i).split("-")[0] != username) {
                competencies.remove(i);
            }
        }
        return competencies;

    }

}
