/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StubClass;

import businesslayer.Procedure;
import dataaccesslayer.MaintenanceProcedureDao;
import java.util.ArrayList;

/**
 *
 * @author Marco Calabrese
 */
public class MaintenanceProcedureDaoImplStub implements MaintenanceProcedureDao {

    static ArrayList<Procedure> allProcedure;
    static ArrayList<String> allProcedureCompetence;//name-competence_i-esima

    public MaintenanceProcedureDaoImplStub() {
        allProcedure = new ArrayList<>();
        allProcedureCompetence = new ArrayList<>();
    }

    public boolean isProcedure(String name) {
        
        for (int i = 0; i < allProcedureCompetence.size(); i++) {
            String string = allProcedureCompetence.get(i);
            String[] parts = string.split("-");
            if (name.equals(parts[0])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean insertProcedure(Procedure procedure) {
        
        if (!this.isProcedure(procedure.getName())) {
            for (int i = 0; i < procedure.getCompetencies().size(); i++) {
                allProcedureCompetence.add(procedure.getName() + "-" + procedure.getCompetencies().get(i));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProcedure(String oldName, String newName) {
        
        if (this.isProcedure(oldName)) {
            for (int i = 0; i < allProcedureCompetence.size(); i++) {
                String string = allProcedureCompetence.get(i);
                String[] parts = string.split("-");
                if (oldName == parts[0]) {
                    allProcedureCompetence.add(i, newName + "-" + parts[1]);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteProcedure(String name) {
        
        for (int i = 0; i < allProcedureCompetence.size(); i++) {
            String string = allProcedureCompetence.get(i);
            String[] parts = string.split("-");
            if (name == parts[0]) {
                allProcedureCompetence.remove(i);
            }
        }
        return this.isProcedure(name);
    }

    @Override
    public ArrayList<Procedure> getAllProcedures() {
        String name = "";
        
        ArrayList<String> competencies = new ArrayList<>();
        for (int i = 0; i < allProcedureCompetence.size(); i++) {
            String string = allProcedureCompetence.get(i);
            String[] parts = string.split("-");
            name = parts[0]; // name
            competencies = this.getCompetenciesByName(name); //competencies
            allProcedure.add(new Procedure(name, competencies));
        }
        return allProcedure;
    }

    public ArrayList<String> getCompetenciesByName(String name) {
        ArrayList<String> competencies = new ArrayList<>();
        
        for (int i = 0; i < allProcedureCompetence.size(); i++) {
            String string = allProcedureCompetence.get(i);
            String[] parts = string.split("-");
            String part1 = parts[0]; // name
            String part2 = parts[1]; // i-esima competence
            if (part1 == name) {
                competencies.add(part2);
            }
        }
        return competencies;
    }
}
