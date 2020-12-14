/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import java.util.ArrayList;

/**
 *
 * @author marku
 */
public class Procedure {

    private String name;
    private ArrayList<String> competencies;

    public Procedure(String name, ArrayList<String> competencies ) {
        this.name = name;
        this.competencies = competencies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(ArrayList<String> competencies) {
        this.competencies = competencies;
    }

    
}
