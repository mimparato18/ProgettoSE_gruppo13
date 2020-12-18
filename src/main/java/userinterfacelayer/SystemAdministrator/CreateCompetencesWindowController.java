/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterfacelayer.SystemAdministrator;

import businesslayer.SystemAdministratorService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author camil
 */
public class CreateCompetencesWindowController implements Initializable {

    @FXML
    private TextField txtCompe;

    public CreateCompetencesWindowController(SystemAdministratorService admin) {
        this.admin=admin;
    }
    
    private SystemAdministratorService admin;
    
    @FXML
    private Button btnDone;
    @FXML
    private Label labResult;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDone_OnAction(ActionEvent event) {
        labResult.setVisible(false);
        labResult.setTextFill(Color.GREEN);
        labResult.setText("Done");

        if (!txtCompe.getText().isBlank()) {

            if (admin.addCompetence(txtCompe.getText())) {
                labResult.setVisible(true);
                txtCompe.clear();
            } else {
                labResult.setText("Add failed");
                labResult.setTextFill(Color.RED);
                labResult.setVisible(true);
            }

        } else {
            labResult.setText("Data missing");
            labResult.setTextFill(Color.RED);
            labResult.setVisible(true);
        }
    }
    
}
