/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslayer;

import java.util.List;

/**
 *
 * @author marku
 */
public class Maintainer extends User{
    private List<String> competencies; 

    public Maintainer(String username, String password) {
        super(username, password);
    }
    
}
