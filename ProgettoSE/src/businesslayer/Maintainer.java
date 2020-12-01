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
public class Maintainer extends User{
    private ArrayList<String> competencies; 

    public Maintainer(String username, String password) {
        super(username, password);
    }
    
    public Maintainer(String username, String password, ArrayList<String> competencies) {
        super(username, password);
        this.competencies= competencies;
    }

    public ArrayList<String> getCompetencies() {
        return competencies;
    }

    public void setCompetencies(ArrayList<String> competencies) {
        this.competencies = competencies;
    }
    
}
